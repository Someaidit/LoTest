package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature020 implements UselessFeature {

    @Override
    public int getId() {
        return 20;
    }

    @Override
    public String getName() {
        return "Useless Feature 020";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 020: it proudly does absolutely nothing useful.";
    }
}
