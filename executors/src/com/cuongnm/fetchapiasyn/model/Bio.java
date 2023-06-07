package com.cuongnm.fetchapiasyn.model;

public class Bio {
    String name;
    Integer age;
    String gender;
    String location;

    public Bio(String name, Integer age, String gender, String location) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Bio{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
