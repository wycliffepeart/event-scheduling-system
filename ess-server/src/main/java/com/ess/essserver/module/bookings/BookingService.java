package com.ess.essserver.module.bookings;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    public List<BookingModel> getBookings(int count) {
        return BookingRepository.getBookings(count);
    }

    public BookingModel getBookingById(int id) {
        return getBookings(10).stream()
                .filter(booking -> booking.getId() == id)
                .findFirst()
                .orElse(null);
    }
}