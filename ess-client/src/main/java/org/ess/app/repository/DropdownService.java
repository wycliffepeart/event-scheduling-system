package org.ess.app.repository;

import org.ess.app.common.HttpClient;
import retrofit2.Callback;

import java.util.List;

public class DropdownService {

    public void get(String type, Callback<List<DropdownDTO>> callback) {
        HttpClient.use(DropdownRepository.class).get(type).enqueue(callback);
    }
}
