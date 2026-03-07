package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature004 implements UselessFeature {

    @Override
    public int getId() {
        return 4;
    }

    @Override
    public String getName() {
        return "Useless Feature 004";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 004: it proudly does absolutely nothing useful.";
    }
}
