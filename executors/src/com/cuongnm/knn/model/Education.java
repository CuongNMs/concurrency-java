package com.cuongnm.knn.model;

public enum Education {
    unknown("unknown"),
    secondary("secondary"),
    primary("primary"),
    tertiary("tertiary");


    private final String label;

    Education(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
