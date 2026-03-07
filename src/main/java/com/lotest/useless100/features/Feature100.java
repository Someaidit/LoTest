package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature100 implements UselessFeature {

    @Override
    public int getId() {
        return 100;
    }

    @Override
    public String getName() {
        return "Useless Feature 100";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 100: it proudly does absolutely nothing useful.";
    }
}
