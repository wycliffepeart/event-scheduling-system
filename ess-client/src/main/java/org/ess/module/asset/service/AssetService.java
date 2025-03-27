package org.ess.module.asset.service;

import org.ess.app.common.HttpClient;
import org.ess.module.asset.model.AssetModel;
import org.ess.module.asset.repository.AssetRepository;
import retrofit2.Callback;

import java.util.List;


public class AssetService {

    /**
     * Fetches a list of all asset models asynchronously and processes the result using a callback.
     *
     * @param callback the callback to handle the response containing a list of asset models or an error
     */
    public void get(Callback<List<AssetModel>> callback) {
        HttpClient.use(AssetRepository.class).get().enqueue(callback);
    }

    /**
     * Fetches an asset model by ID asynchronously and processes the result using a callback.
     *
     * @param callback the callback to handle the response containing the asset model or an error
     */
    public void post(AssetModel assetModel, Callback<AssetModel> callback) {
        HttpClient.use(AssetRepository.class).post(assetModel).enqueue(callback);
    }

    /**
     * Updates an asset model asynchronously and processes the result using a callback.
     *
     * @param assetModel the asset model to update
     * @param callback   the callback to handle the response containing the updated asset model or an error
     */
    public void put(AssetModel assetModel, Callback<AssetModel> callback) {
        HttpClient.use(AssetRepository.class).put(assetModel.getId(), assetModel).enqueue(callback);
    }

    /**
     * Deletes an asset model by ID asynchronously and processes the result using a callback.
     *
     * @param id       the ID of the asset model to delete
     * @param callback the callback to handle the response containing a boolean indicating success or an error
     */
    public void delete(long id, Callback<Boolean> callback) {
        HttpClient.use(AssetRepository.class).delete(id).enqueue(callback);
    }
}
