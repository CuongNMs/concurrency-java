package com.cuongnm.fetchapiasyn.biz;

import com.cuongnm.fetchapiasyn.model.Profile;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Instant start = Instant.now();
        FetchDataAsync async = new FetchDataAsync();
        List<Profile> profiles = async.fetchUserProfiles(Arrays.asList("andrew", "billy", "charlie", "david", "emma"));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Time elapsed " + timeElapsed);
        System.out.println("Profiles " + profiles);
        System.exit(0);
    }
}
