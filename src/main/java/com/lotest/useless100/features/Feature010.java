package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature010 implements UselessFeature {

    @Override
    public int getId() {
        return 10;
    }

    @Override
    public String getName() {
        return "Useless Feature 010";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 010: it proudly does absolutely nothing useful.";
    }
}
