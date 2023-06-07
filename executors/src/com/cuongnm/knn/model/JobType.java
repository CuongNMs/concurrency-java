package com.cuongnm.knn.model;

public enum JobType {
    admin("admin"),
    unknown("unknown"),
    unemployed("unemployed"),
    management("management"),
    housemaid("housemaid"),
    entrepreneur("entrepreneur"),
    student("student"),
    bluecollar("blue-collar"),
    selfemployed("self-employed"),
    retired("retired"),
    technician("technician"),
    services("services");

    private final String label;

    JobType(String label){
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
