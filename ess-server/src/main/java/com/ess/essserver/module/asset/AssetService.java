package com.ess.essserver.module.asset;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    public List<AssetModel> getAssets(int count) {
        return AssetRepository.getAssets(count);
    }

    public AssetModel getAssetById(int id) {
        return getAssets(10).stream()
                .filter(asset -> asset.getId() == id)
                .findFirst()
                .orElse(null);
    }
}