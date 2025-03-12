package com.ess.essserver.module.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AssetModel {

    private int id;

    private String name;

    private String category;

    private int quantity;

    private String condition;

    private String status;

    private String createdAt;

    private String updatedAt;
}