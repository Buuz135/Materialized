package com.buuz135.materialized.api.material.info;

import lombok.Data;

public @Data
class ItemPart {

    private String type;
    private String[] oredict;

    public ItemPart(String type, String... oredict) {
        this.type = type;
        this.oredict = oredict;
    }
}
