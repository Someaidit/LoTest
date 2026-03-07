package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature007 implements UselessFeature {

    @Override
    public int getId() {
        return 7;
    }

    @Override
    public String getName() {
        return "Useless Feature 007";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 007: it proudly does absolutely nothing useful.";
    }
}
