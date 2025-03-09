package org.ess.module.asset.service;

import org.ess.app.common.HttpClient;
import org.ess.module.asset.model.AssetModel;
import org.ess.module.asset.repository.AssetRepository;
import retrofit2.Callback;

import java.util.List;


public class AssetService {

    public void get(Callback<List<AssetModel>> callback) {
        HttpClient.use(AssetRepository.class).get().enqueue(callback);
    }

    public void post(AssetModel assetModel, Callback<AssetModel> callback) {
        HttpClient.use(AssetRepository.class).post(assetModel).enqueue(callback);
    }

    public void delete(AssetModel assetModel, Callback<Boolean> callback) {
        HttpClient.use(AssetRepository.class).delete(assetModel.getId()).enqueue(callback);
    }
}
