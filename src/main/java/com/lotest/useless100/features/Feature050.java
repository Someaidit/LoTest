package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature050 implements UselessFeature {

    @Override
    public int getId() {
        return 50;
    }

    @Override
    public String getName() {
        return "Useless Feature 050";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 050: it proudly does absolutely nothing useful.";
    }
}
