package org.ess.module.event.repository;

import com.github.javafaker.Faker;
import org.ess.module.asset.model.AssetModel;
import org.ess.module.event.model.EventModel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class EventFakeRepository {

    public static List<EventModel> getData(int count) {

        Function<LocalDate, String> formatDate = (LocalDate date) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(formatter);
        };

        return IntStream.rangeClosed(1, count)
                .boxed()
                .toList().stream()
                .map(i -> new EventModel()
                        .setId(i)
                        .setName(Faker.instance().name().username())
                        .setLocation(Faker.instance().name().lastName())
                        .setStartDate(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setEndDate(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setStartTime(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setEndTime(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setStatus(Faker.instance().name().username())
                        .setCreatedBy(Faker.instance().name().username())
                        .setUpdatedAt(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                        .setCreatedAt(String.valueOf(LocalDate.parse(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))))
                )
                .toList();
    }
}
