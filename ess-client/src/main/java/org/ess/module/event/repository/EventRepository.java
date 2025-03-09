package org.ess.module.event.repository;

import org.ess.module.event.model.EventModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface EventRepository {

    @GET("users")
    Call<List<EventModel>> get();

    @POST("users")
    Call<EventModel> post(@Body EventModel assetModel);

    @DELETE("users/{id}")
    Call<Boolean> delete(@Path("id") int id);
}
