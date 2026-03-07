package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature001 implements UselessFeature {

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public String getName() {
        return "Useless Feature 001";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 001: it proudly does absolutely nothing useful.";
    }
}
