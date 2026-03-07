package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature025 implements UselessFeature {

    @Override
    public int getId() {
        return 25;
    }

    @Override
    public String getName() {
        return "Useless Feature 025";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 025: it proudly does absolutely nothing useful.";
    }
}
