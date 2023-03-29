package com.cuongnm.executors.model;

public enum Poutcome {
    unknown("unknown"),other("other"),failure("failure"),success("success");
    private final String label;

    Poutcome(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
