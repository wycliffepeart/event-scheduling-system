package org.ess.module.bookings.repository;

import org.ess.module.bookings.model.BookingModel;
import org.ess.module.event.model.EventModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BookingRepository {

    @GET("users")
    Call<List<BookingModel>> get();

    @POST("users")
    Call<BookingModel> post(@Body BookingModel bookingModel);

    @DELETE("users/{id}")
    Call<Boolean> delete(@Path("id") int id);
}
