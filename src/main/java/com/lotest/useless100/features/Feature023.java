package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature023 implements UselessFeature {

    @Override
    public int getId() {
        return 23;
    }

    @Override
    public String getName() {
        return "Useless Feature 023";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 023: it proudly does absolutely nothing useful.";
    }
}
