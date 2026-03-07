package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature011 implements UselessFeature {

    @Override
    public int getId() {
        return 11;
    }

    @Override
    public String getName() {
        return "Useless Feature 011";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 011: it proudly does absolutely nothing useful.";
    }
}
