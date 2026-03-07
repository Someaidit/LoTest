package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature013 implements UselessFeature {

    @Override
    public int getId() {
        return 13;
    }

    @Override
    public String getName() {
        return "Useless Feature 013";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 013: it proudly does absolutely nothing useful.";
    }
}
