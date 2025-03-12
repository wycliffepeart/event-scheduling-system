package com.ess.essserver.module.bookings;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class BookingRepository {

    public static List<BookingModel> getBookings(int count) {

        Function<LocalDate, String> formatDate = (LocalDate date) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(formatter);
        };

        return IntStream.rangeClosed(1, count)
                .boxed()
                .toList().stream()
                .map(i -> new BookingModel()
                        .setId(i)
                        .setEventId(Faker.instance().number().randomNumber())
                        .setAssetId(Faker.instance().number().randomNumber())
                        .setStartDate(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                        .setStartTime(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalTime().toString())
                        .setEndDate(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                        .setEndTime(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalTime().toString())
                        .setCreatedAt(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                        .setUpdatedAt(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                )
                .toList();
    }
}