package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature021 implements UselessFeature {

    @Override
    public int getId() {
        return 21;
    }

    @Override
    public String getName() {
        return "Useless Feature 021";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 021: it proudly does absolutely nothing useful.";
    }
}
