package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature002 implements UselessFeature {

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public String getName() {
        return "Useless Feature 002";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 002: it proudly does absolutely nothing useful.";
    }
}
