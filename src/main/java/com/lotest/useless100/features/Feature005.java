package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature005 implements UselessFeature {

    @Override
    public int getId() {
        return 5;
    }

    @Override
    public String getName() {
        return "Useless Feature 005";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 005: it proudly does absolutely nothing useful.";
    }
}
