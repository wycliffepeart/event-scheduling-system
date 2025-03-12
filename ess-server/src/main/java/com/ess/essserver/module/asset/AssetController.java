package com.ess.essserver.module.asset;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final List<AssetModel> assetModels = AssetRepository.getAssets(10);

    // A simple GET endpoint
    @GetMapping
    public List<AssetModel> getAssets() {
        return assetModels;
    }

    @GetMapping("/{id}")
    public AssetModel findById(@PathVariable int id) {
        return assetModels.stream().filter(asset -> asset.getId() == id).findFirst().orElse(null);
    }
}
