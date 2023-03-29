package com.cuongnm.executors.model;

public enum Month {
    jan("january"),
    feb("february"),
    mar("march"),
    apr("april"),
    may("may"),
    jun("june"),
    jul("july"),
    aug("august"),
    sep("september"),
    oct("october"),
    nov("november"),
    dec("december");

    private final String label;

    Month(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
