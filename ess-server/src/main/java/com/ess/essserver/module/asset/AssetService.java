package com.ess.essserver.module.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    // The following methods are the CRUD operations for the Asset entity
    public List<AssetResponseDTO> getAllAssets() {
        return assetRepository.findAll().stream()
                .map(assetMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // The following methods are the CRUD operations for the Asset entity
    public AssetResponseDTO getAssetById(Long id) {
        AssetEntity asset = assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + id));
        return assetMapper.toResponseDTO(asset);
    }

    // The following methods are the CRUD operations for the Asset entity
    public AssetResponseDTO createAsset(AssetRequestDTO dto) {
        AssetEntity asset = assetMapper.toEntity(dto);
        asset = assetRepository.save(asset);
        return assetMapper.toResponseDTO(asset);
    }

    // The following methods are the CRUD operations for the Asset entity
    public AssetResponseDTO updateAsset(Long id, AssetRequestDTO dto) {
        AssetEntity asset = assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + id));

        asset.setName(dto.getName());
        asset.setCategory(dto.getCategory());
        asset.setQuantity(dto.getQuantity());
        asset.setStatus(dto.getStatus());

        asset = assetRepository.save(asset);
        return assetMapper.toResponseDTO(asset);
    }

    // The following methods are the CRUD operations for the Asset entity
    public void deleteAsset(Long id) {
        AssetEntity asset = assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + id));
        assetRepository.delete(asset);
    }
}
