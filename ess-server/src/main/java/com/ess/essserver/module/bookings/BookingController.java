package com.ess.essserver.module.bookings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final List<BookingModel> bookingModels = BookingRepository.getBookings(10);

    @GetMapping
    public List<BookingModel> getBookings() {
        return bookingModels;
    }

    @GetMapping("/{id}")
    public BookingModel findById(@PathVariable int id) {
        return bookingModels.stream().filter(booking -> booking.getId() == id).findFirst().orElse(null);
    }
}