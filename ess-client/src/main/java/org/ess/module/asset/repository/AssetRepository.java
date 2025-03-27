package org.ess.module.asset.repository;

import org.ess.module.asset.model.AssetModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AssetRepository {

    /**
     * Retrieves an asset from the server.
     *
     * @param id the ID of the asset to retrieve
     * @return the asset
     */
    @GET("/api/assets/{id}")
    Call<Boolean> get(@Path("id") int id);

    /**
     * Updates an asset on the server.
     *
     * @param id         the ID of the asset to update
     * @param assetModel the updated asset
     * @return the updated asset
     */
    @PUT("/api/assets/{id}")
    Call<AssetModel> put(@Path("id") long id, @Body AssetModel assetModel);

    /**
     * Deletes an asset from the server.
     *
     * @param id the ID of the asset to delete
     * @return true if the asset was deleted successfully, false otherwise
     */
    @DELETE("/api/assets/{id}")
    Call<Boolean> delete(@Path("id") long id);

    /**
     * Retrieves all assets from the server.
     *
     * @return the list of assets
     */
    @GET("/api/assets")
    Call<List<AssetModel>> get();

    /**
     * Creates a new asset on the server.
     *
     * @param assetModel the asset to create
     * @return the created asset
     */
    @POST("/api/assets")
    Call<AssetModel> post(@Body AssetModel assetModel);
}
