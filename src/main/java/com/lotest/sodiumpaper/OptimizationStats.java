package com.lotest.sodiumpaper;

public class OptimizationStats {
    private int entitiesWithoutAi;
    private int itemsMerged;

    public void reset() {
        entitiesWithoutAi = 0;
        itemsMerged = 0;
    }

    public void setEntitiesWithoutAi(int entitiesWithoutAi) {
        this.entitiesWithoutAi = entitiesWithoutAi;
    }

    public void setItemsMerged(int itemsMerged) {
        this.itemsMerged = itemsMerged;
    }

    public int getEntitiesWithoutAi() {
        return entitiesWithoutAi;
    }

    public int getItemsMerged() {
        return itemsMerged;
    }
}
