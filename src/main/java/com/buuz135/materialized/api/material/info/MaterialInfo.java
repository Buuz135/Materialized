package com.buuz135.materialized.api.material.info;

import lombok.Builder;
import lombok.Data;

import java.util.List;

public @Builder
@Data
class MaterialInfo {

    private String name;
    private String color;
    private List<BlockPart> blockParts;
    private boolean block;

}
