package org.ess.module.event.service;

import org.ess.module.event.model.EventModel;
import retrofit2.Callback;

import java.util.List;

public interface EventServiceI {

    void get(Callback<List<EventModel>> callback);

    void post(EventModel eventModel, Callback<EventModel> callback);

    void put(EventModel eventModel, Callback<EventModel> callback);

    void delete(long id, Callback<Boolean> callback);
}
