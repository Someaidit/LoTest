package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature080 implements UselessFeature {

    @Override
    public int getId() {
        return 80;
    }

    @Override
    public String getName() {
        return "Useless Feature 080";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 080: it proudly does absolutely nothing useful.";
    }
}
