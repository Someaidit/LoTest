package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature006 implements UselessFeature {

    @Override
    public int getId() {
        return 6;
    }

    @Override
    public String getName() {
        return "Useless Feature 006";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 006: it proudly does absolutely nothing useful.";
    }
}
