package com.lotest.useless100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureRegistry {

    private final List<UselessFeature> features = new ArrayList<>();
    private final Map<Integer, UselessFeature> featureById = new HashMap<>();

    public FeatureRegistry() {
        for (int i = 1; i <= 100; i++) {
            String className = String.format("com.lotest.useless100.features.Feature%03d", i);
            try {
                Class<?> clazz = Class.forName(className);
                UselessFeature feature = (UselessFeature) clazz.getDeclaredConstructor().newInstance();
                features.add(feature);
                featureById.put(feature.getId(), feature);
            } catch (ReflectiveOperationException ex) {
                throw new IllegalStateException("Unable to load " + className, ex);
            }
        }
    }

    public int size() {
        return features.size();
    }

    public List<UselessFeature> getFeatures() {
        return Collections.unmodifiableList(features);
    }

    public UselessFeature getFeature(int id) {
        return featureById.get(id);
    }
}
