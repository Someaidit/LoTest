package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature030 implements UselessFeature {

    @Override
    public int getId() {
        return 30;
    }

    @Override
    public String getName() {
        return "Useless Feature 030";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 030: it proudly does absolutely nothing useful.";
    }
}
