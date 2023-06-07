package com.cuongnm.fetchapiasyn.biz;

import com.cuongnm.fetchapiasyn.model.Bio;
import com.cuongnm.fetchapiasyn.model.Profile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FetchDataAsync {
    public ExecutorService executor = Executors.newFixedThreadPool(20);

    public List<Profile> fetchUserProfiles(List<String> profileId) {
        List<CompletableFuture<Profile>> future = profileId.stream().map(id -> fetchUserProfileAsync(id)).collect(Collectors.toList());
        return future.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private CompletableFuture<Profile> fetchUserProfileAsync(String profileId) {
        return CompletableFuture.supplyAsync(() -> {
            List<Object> result = Stream.of(fetchBioOverHttpAsync(profileId), fetchPictureFromS3Async(profileId), fetchDocumentFromFtpAsync(profileId))
                    .map(CompletableFuture::join).collect(Collectors.toList());
            return new Profile((Bio) result.get(0), (List<String>) result.get(1), (List<String>) result.get(2));
        }, executor);
    }

    private CompletableFuture<List<String>> fetchDocumentFromFtpAsync(String profileId) {
        return CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(1000);// simulate api call
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Arrays.asList("Document of profile " + profileId);
        }, executor);
    }

    private CompletableFuture<List<String>> fetchPictureFromS3Async(String profileId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // simulate 2 sec to fetch api pictures
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Arrays.asList("picture of " + profileId);
        }, executor);
    }

    private CompletableFuture<Bio> fetchBioOverHttpAsync(String profileId) {
        return CompletableFuture.supplyAsync(() ->{
            try {
                Thread.sleep(3000); //simulate api call
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random random = new Random();
            List<String> gender = Arrays.asList("male", "female", "na");
            char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            Collections.shuffle(gender);
            return new Bio(profileId, random.nextInt(100), gender.get(0), "location " + alphabet[random.nextInt(25)+1]);
        }, executor);
    }


}
