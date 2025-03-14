package com.ess.essserver.module.asset.price;

import com.ess.essserver.module.asset.AssetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AssetPriceMapper {

    private final AssetRepository assetRepository;

    public AssetPriceEntity toEntity(Long assetId, AssetPriceRequestDTO dto) {
        return AssetPriceEntity.builder()
                .asset(assetRepository.getReferenceById(assetId))
                .price(dto.getPrice())
                .status(dto.getStatus())
                .build();
    }

    public AssetPriceResponseDTO toResponseDTO(AssetPriceEntity entity) {
        return AssetPriceResponseDTO.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}