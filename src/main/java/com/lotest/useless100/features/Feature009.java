package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature009 implements UselessFeature {

    @Override
    public int getId() {
        return 9;
    }

    @Override
    public String getName() {
        return "Useless Feature 009";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 009: it proudly does absolutely nothing useful.";
    }
}
