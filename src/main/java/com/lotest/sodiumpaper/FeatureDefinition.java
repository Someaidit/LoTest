package com.lotest.sodiumpaper;

public class FeatureDefinition {
    private final String key;
    private final String description;
    private final boolean defaultEnabled;

    public FeatureDefinition(String key, String description, boolean defaultEnabled) {
        this.key = key;
        this.description = description;
        this.defaultEnabled = defaultEnabled;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDefaultEnabled() {
        return defaultEnabled;
    }
}
