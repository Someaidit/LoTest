package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature003 implements UselessFeature {

    @Override
    public int getId() {
        return 3;
    }

    @Override
    public String getName() {
        return "Useless Feature 003";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 003: it proudly does absolutely nothing useful.";
    }
}
