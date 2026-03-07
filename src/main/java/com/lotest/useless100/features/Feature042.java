package com.lotest.useless100.features;

import com.lotest.useless100.UselessFeature;

public class Feature042 implements UselessFeature {

    @Override
    public int getId() {
        return 42;
    }

    @Override
    public String getName() {
        return "Useless Feature 042";
    }

    @Override
    public String getMessage() {
        return "This is useless feature 042: it proudly does absolutely nothing useful.";
    }
}
