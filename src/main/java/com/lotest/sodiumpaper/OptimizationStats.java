package com.lotest.sodiumpaper;

public class OptimizationStats {
    private int entitiesWithoutAi;
    private int itemsMerged;
    private int cleanedEntities;
    private int gamerulesApplied;
    private int worldsScanned;
    private int worldsSkipped;
    private long cycleTimeMs;

    public void reset() {
        entitiesWithoutAi = 0;
        itemsMerged = 0;
        cleanedEntities = 0;
        gamerulesApplied = 0;
        worldsScanned = 0;
        worldsSkipped = 0;
        cycleTimeMs = 0L;
    }

    public int getEntitiesWithoutAi() { return entitiesWithoutAi; }
    public void setEntitiesWithoutAi(int entitiesWithoutAi) { this.entitiesWithoutAi = entitiesWithoutAi; }
    public int getItemsMerged() { return itemsMerged; }
    public void setItemsMerged(int itemsMerged) { this.itemsMerged = itemsMerged; }
    public int getCleanedEntities() { return cleanedEntities; }
    public void setCleanedEntities(int cleanedEntities) { this.cleanedEntities = cleanedEntities; }
    public int getGamerulesApplied() { return gamerulesApplied; }
    public void setGamerulesApplied(int gamerulesApplied) { this.gamerulesApplied = gamerulesApplied; }
    public int getWorldsScanned() { return worldsScanned; }
    public void setWorldsScanned(int worldsScanned) { this.worldsScanned = worldsScanned; }
    public int getWorldsSkipped() { return worldsSkipped; }
    public void setWorldsSkipped(int worldsSkipped) { this.worldsSkipped = worldsSkipped; }
    public long getCycleTimeMs() { return cycleTimeMs; }
    public void setCycleTimeMs(long cycleTimeMs) { this.cycleTimeMs = cycleTimeMs; }
}
