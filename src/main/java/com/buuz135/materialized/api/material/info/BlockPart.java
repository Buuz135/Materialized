package com.buuz135.materialized.api.material.info;

import lombok.Data;

public @Data
class BlockPart {

    private String type;
    private int harvestLevel;
    private DropInfo drop;
    private String[] oredict;
    private float hardness;

    public BlockPart(String type, int harvestLevel, float hardness, DropInfo drop, String... oredict) {
        this.type = type;
        this.harvestLevel = harvestLevel;
        this.drop = drop;
        this.oredict = oredict;
        this.hardness = hardness;
    }

    public @Data
    static class DropInfo {
        private String item;
        private int meta;
        private int amount;
        private int fortuneModifier;
        private int xpDrop;

        public DropInfo(String item, int meta, int amount, int fortuneModifier, int xpDrop) {
            this.item = item;
            this.meta = meta;
            this.amount = amount;
            this.fortuneModifier = fortuneModifier;
            this.xpDrop = xpDrop;
        }
    }
}
