package com.cuongnm.fetchapiasyn.model;

import java.util.List;

public class Profile {
    Bio bio;
    List<String> pictures;
    List<String> documents;

    public Profile(Bio bio, List<String> pictures, List<String> documents) {
        this.bio = bio;
        this.pictures = pictures;
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "bio=" + bio +
                ", pictures=" + pictures +
                ", documents=" + documents +
                '}';
    }
}
