package org.ess.app.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.LocalDate;


public class HttpClient {

    public static <T> T use(final Class<T> repository) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(repository);
    }
}

