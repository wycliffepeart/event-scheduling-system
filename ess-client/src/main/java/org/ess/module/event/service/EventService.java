package org.ess.module.event.service;

import org.ess.app.common.HttpClient;
import org.ess.module.event.model.EventModel;
import org.ess.module.event.repository.EventRepository;
import retrofit2.Callback;

import java.util.List;

public class EventService implements EventServiceI {

    /**
     * Fetches a list of all event models asynchronously and processes the result using a callback.
     *
     * @param callback the callback to handle the response containing a list of event models or an error
     */
    public void get(Callback<List<EventModel>> callback) {
        HttpClient.use(EventRepository.class).get().enqueue(callback);
    }

    /**
     * Fetches an event model by ID asynchronously and processes the result using a callback.
     *
     * @param eventModel the event model to post
     * @param callback   the callback to handle the response containing the event model or an error
     */
    public void post(EventModel eventModel, Callback<EventModel> callback) {
        HttpClient.use(EventRepository.class).post(eventModel).enqueue(callback);
    }

    /**
     * Updates an event model asynchronously and processes the result using a callback.
     *
     * @param eventModel the event model to update
     * @param callback   the callback to handle the response containing the updated event model or an error
     */
    public void put(EventModel eventModel, Callback<EventModel> callback) {
        HttpClient.use(EventRepository.class).put(eventModel.getId(), eventModel).enqueue(callback);
    }

    /**
     * Deletes an event model by ID asynchronously and processes the result using a callback.
     *
     * @param id       the ID of the event model to delete
     * @param callback the callback to handle the response containing a boolean indicating success or an error
     */
    public void delete(long id, Callback<Boolean> callback) {
        HttpClient.use(EventRepository.class).delete(id).enqueue(callback);
    }
}
