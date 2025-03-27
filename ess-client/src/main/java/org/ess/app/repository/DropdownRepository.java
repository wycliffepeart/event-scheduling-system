package org.ess.app.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface DropdownRepository {

    @GET("/api/dropdowns")
    Call<List<DropdownDTO>> get(@Query("type") String type);
}
