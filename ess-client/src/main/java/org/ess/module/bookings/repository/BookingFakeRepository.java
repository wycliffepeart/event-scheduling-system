package org.ess.module.bookings.repository;

import com.github.javafaker.Faker;
import org.ess.module.bookings.model.BookingModel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class BookingFakeRepository {

    public static List<BookingModel> getData(int count) {

        Function<LocalDate, String> formatDate = (LocalDate date) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(formatter);
        };

        return IntStream.rangeClosed(1, count)
                .boxed()
                .toList().stream()
                .map(i -> new BookingModel()
                        .setId(i)
                        .setEventId(Faker.instance().number().numberBetween(1, 100))
                        .setAssetId(Faker.instance().number().numberBetween(1, 100))
                        .setStartDate(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setStartTime(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setEndDate(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setEndTime(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setUpdatedAt(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setCreatedAt(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                )
                .toList();
    }
}
