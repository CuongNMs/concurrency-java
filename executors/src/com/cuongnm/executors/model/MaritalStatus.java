package com.cuongnm.executors.model;

public enum MaritalStatus {
    married("married"),
    divorced("divorced"),
    single("single");

    private final String label;
    MaritalStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
