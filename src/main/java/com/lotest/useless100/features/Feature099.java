package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature099 implements UselessFeature {

    @Override
    public int getId() {
        return 99;
    }

    @Override
    public String getName() {
        return "Useless Feature 099";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 099: it proudly does absolutely nothing useful.";
    }
}
