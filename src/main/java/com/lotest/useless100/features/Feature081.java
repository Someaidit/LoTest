package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature081 implements UselessFeature {

    @Override
    public int getId() {
        return 81;
    }

    @Override
    public String getName() {
        return "Useless Feature 081";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 081: it proudly does absolutely nothing useful.";
    }
}
