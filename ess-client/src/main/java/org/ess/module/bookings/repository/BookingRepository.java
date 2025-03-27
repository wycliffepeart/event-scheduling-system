package org.ess.module.bookings.repository;

import org.ess.module.bookings.model.BookingRequest;
import org.ess.module.bookings.model.BookingResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface BookingRepository {

    /**
     * Retrieves a list of bookings from the server.
     *
     * @param options the query options to filter the bookings
     * @return the list of bookings
     */
    @GET("/api/bookings")
    Call<List<BookingResponse>> get(@QueryMap Map<String, String> options);

    /**
     * Creates a new booking on the server.
     *
     * @param bookingModel the booking to create
     * @return the created booking
     */
    @POST("/api/bookings")
    Call<BookingResponse> post(@Body BookingRequest bookingModel);

    /**
     * Retrieves a booking from the server.
     *
     * @param id the ID of the booking to retrieve
     * @return the booking
     */
    @GET("/api/bookings/{id}")
    Call<BookingResponse> get(@Path("id") long id);

    /**
     * Updates a booking on the server.
     *
     * @param id           the ID of the booking to update
     * @param bookingModel the updated booking
     * @return the updated booking
     */
    @PUT("/api/bookings/{id}")
    Call<BookingResponse> put(@Path("id") long id, @Body BookingRequest bookingModel);

    /**
     * Deletes a booking from the server.
     *
     * @param id the ID of the booking to delete
     * @return true if the booking was deleted successfully, false otherwise
     */
    @DELETE("/api/bookings/{id}")
    Call<Boolean> delete(@Path("id") long id);

}
