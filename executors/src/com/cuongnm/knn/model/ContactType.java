package com.cuongnm.knn.model;

public enum ContactType {
    unknown("unknown"),telephone("telephone"),tertiary("tertiary");
    private final String label;

    ContactType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
