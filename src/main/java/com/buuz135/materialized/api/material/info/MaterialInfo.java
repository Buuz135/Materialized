package com.buuz135.materialized.api.material.info;

import lombok.Builder;
import lombok.Data;

public @Builder
@Data
class MaterialInfo {

    private String name;
    private String color;
    private OrePartInfo ore;
    private OrePartInfo denseore;
    private OrePartInfo lightore;
    private OrePartInfo gravelore;
    private OrePartInfo sandore;
    private boolean block;

}
