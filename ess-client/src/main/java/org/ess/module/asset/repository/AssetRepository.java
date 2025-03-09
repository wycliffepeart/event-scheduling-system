package org.ess.module.asset.repository;

import org.ess.module.asset.model.AssetModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AssetRepository {

    @GET("users")
    Call<List<AssetModel>> get();

    @POST("users")
    Call<AssetModel> post(@Body AssetModel assetModel);

    @DELETE("users/{id}")
    Call<Boolean> delete(@Path("id") int id);
}
