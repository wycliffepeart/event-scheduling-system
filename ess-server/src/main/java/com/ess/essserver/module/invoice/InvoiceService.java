package com.ess.essserver.module.invoice;

import com.ess.essserver.app.PaymentStatus;
import com.ess.essserver.module.booking.BookingEntity;
import com.ess.essserver.module.booking.BookingRepository;
import com.ess.essserver.module.event.EventEntity;
import com.ess.essserver.module.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceMapper invoiceMapper;
    private final EventRepository eventRepository;
    private final InvoiceRepository invoiceRepository;
    private final BookingRepository bookingRepository;

    // The following methods are the CRUD operations for the Invoice entity
    public List<InvoiceResponseDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // The following methods are the CRUD operations for the Invoice entity
    public InvoiceResponseDTO getInvoiceById(Long id) {

        InvoiceEntity invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));

        return invoiceMapper.toResponseDTO(invoice);
    }

    // The following methods are the CRUD operations for the Invoice entity
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO dto) {
        EventEntity event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("event not found with ID: " + dto.getEventId()));

        List<BookingEntity> bookings = bookingRepository.findAllByIdInAndEventAndPaymentStatus(dto.getBookingIds(), event, PaymentStatus.PENDING);

        if (bookings.isEmpty()) throw new RuntimeException("event not found with ID: " + dto.getEventId());

        var total = bookings.stream().mapToDouble(booking -> {
            long daysBetween = ChronoUnit.DAYS.between(booking.getStartTime(), booking.getEndTime());
            booking.setPrice(booking.getAsset().getPrice());
            booking.setTotal(booking.getAsset().getPrice() * daysBetween);
            return booking.getTotal();
        }).sum();

        InvoiceEntity toInvoice = invoiceMapper.toEntity(event, bookings, BigDecimal.valueOf(total), "PAID", LocalDateTime.now());
        InvoiceEntity invoice = invoiceRepository.save(toInvoice);

        bookings.forEach(booking -> {
            long daysBetween = ChronoUnit.DAYS.between(booking.getStartTime(), booking.getEndTime());

            booking.setPaymentStatus(PaymentStatus.COMPLETED);
            booking.setInvoiceId(invoice.getId());
            booking.setPrice(booking.getAsset().getPrice());
            booking.setTotal(booking.getAsset().getPrice() * daysBetween);
            bookingRepository.save(booking);
        });

        return invoiceMapper.toResponseDTO(invoice);
    }

}