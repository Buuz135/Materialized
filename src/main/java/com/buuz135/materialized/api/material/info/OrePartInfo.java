package com.buuz135.materialized.api.material.info;

import lombok.Data;

public @Data
class OrePartInfo {

    private int harvestLevel;
    private DropInfo drop;
    private String[] oredict;

    public @Data
    class DropInfo {
        private String item;
        private int meta;
        private int amount;
    }
}
