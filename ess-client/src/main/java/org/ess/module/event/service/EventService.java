package org.ess.module.event.service;

import org.ess.app.common.HttpClient;
import org.ess.module.event.model.EventModel;
import org.ess.module.event.repository.EventRepository;
import retrofit2.Callback;

import java.util.List;

public class EventService {

    public void get(Callback<List<EventModel>> callback) {
        HttpClient.use(EventRepository.class).get().enqueue(callback);
    }

    public void post(EventModel eventModel, Callback<EventModel> callback) {
        HttpClient.use(EventRepository.class).post(eventModel).enqueue(callback);
    }

    public void delete(EventModel eventModel, Callback<Boolean> callback) {
        HttpClient.use(EventRepository.class).delete(eventModel.getId()).enqueue(callback);
    }
}
