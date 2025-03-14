package com.ess.essserver.module.asset;

import org.springframework.stereotype.Component;

@Component
public class AssetMapper {

    public AssetEntity toEntity(AssetRequestDTO dto) {
        return AssetEntity.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .quantity(dto.getQuantity())
                .status(dto.getStatus())
                .build();
    }

    public AssetResponseDTO toResponseDTO(AssetEntity entity) {
        return AssetResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .quantity(entity.getQuantity())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}