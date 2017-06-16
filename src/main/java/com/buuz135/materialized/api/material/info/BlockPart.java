package com.buuz135.materialized.api.material.info;

import lombok.Data;

public @Data
class BlockPart {

    private String type;
    private int harvestLevel;
    private DropInfo drop;
    private String[] oredict;

    public BlockPart(String type, int harvestLevel, DropInfo drop, String... oredict) {
        this.type = type;
        this.harvestLevel = harvestLevel;
        this.drop = drop;
        this.oredict = oredict;
    }

    public @Data
    static class DropInfo {
        private String item;
        private int meta;
        private int amount;


    }
}
