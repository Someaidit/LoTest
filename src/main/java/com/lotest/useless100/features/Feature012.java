package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature012 implements UselessFeature {

    @Override
    public int getId() {
        return 12;
    }

    @Override
    public String getName() {
        return "Useless Feature 012";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 012: it proudly does absolutely nothing useful.";
    }
}
