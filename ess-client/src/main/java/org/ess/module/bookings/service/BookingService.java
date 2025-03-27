package org.ess.module.bookings.service;

import org.ess.app.common.HttpClient;
import org.ess.module.bookings.model.BookingRequest;
import org.ess.module.bookings.model.BookingResponse;
import org.ess.module.bookings.repository.BookingRepository;
import retrofit2.Callback;

import java.util.List;
import java.util.Map;

public class BookingService {

    /**
     * Fetches a list of all booking models asynchronously and processes the result using a callback.
     *
     * @param options  the query options to filter the bookings
     * @param callback the callback to handle the response containing a list of booking models or an error
     */
    public void get(Map<String, String> options, Callback<List<BookingResponse>> callback) {
        HttpClient.use(BookingRepository.class).get(options).enqueue(callback);
    }

    /**
     * Fetches a list of all booking models asynchronously and processes the result using a callback.
     *
     * @param callback the callback to handle the response containing a list of booking models or an error
     */
    public void post(BookingRequest bookingModel, Callback<BookingResponse> callback) {
        HttpClient.use(BookingRepository.class).post(bookingModel).enqueue(callback);
    }

    /**
     * Fetches a booking model by ID asynchronously and processes the result using a callback.
     *
     * @param callback the callback to handle the response containing the booking model or an error
     */
    public void get(long id, Callback<BookingResponse> callback) {
        HttpClient.use(BookingRepository.class).get(id).enqueue(callback);
    }

    /**
     * Updates a booking model asynchronously and processes the result using a callback.
     *
     * @param bookingModel the booking model to update
     * @param callback     the callback to handle the response containing the updated booking model or an error
     */
    public void put(BookingRequest bookingModel, Callback<BookingResponse> callback) {
        HttpClient.use(BookingRepository.class).put(bookingModel.getId(), bookingModel).enqueue(callback);
    }

    /**
     * Deletes a booking model by ID asynchronously and processes the result using a callback.
     *
     * @param id       the ID of the booking model to delete
     * @param callback the callback to handle the response containing a boolean indicating success or an error
     */
    public void delete(long id, Callback<Boolean> callback) {
        HttpClient.use(BookingRepository.class).delete(id).enqueue(callback);
    }
}
