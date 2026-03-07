package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature008 implements UselessFeature {

    @Override
    public int getId() {
        return 8;
    }

    @Override
    public String getName() {
        return "Useless Feature 008";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 008: it proudly does absolutely nothing useful.";
    }
}
