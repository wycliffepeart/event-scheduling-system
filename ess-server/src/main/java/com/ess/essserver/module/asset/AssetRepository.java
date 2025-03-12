package com.ess.essserver.module.asset;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class AssetRepository {

    public static List<AssetModel> getAssets(int count) {

        Function<LocalDate, String> formatDate = (LocalDate date) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(formatter);
        };

        return IntStream.rangeClosed(1, count)
                .boxed()
                .toList().stream()
                .map(i -> new AssetModel()
                        .setName(Faker.instance().name().username())
                        .setCategory(Faker.instance().name().firstName())
                        .setQuantity(Faker.instance().number().numberBetween(1, 100))
                        .setCondition(Faker.instance().animal().name())
                        .setStatus(Faker.instance().animal().name())
                        .setCreatedAt(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                        .setUpdatedAt(formatDate.apply(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                )
                .toList();
    }
}