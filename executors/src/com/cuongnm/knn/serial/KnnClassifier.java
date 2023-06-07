package com.cuongnm.knn.serial;

import com.cuongnm.knn.Distance;
import com.cuongnm.knn.EuclideanDistanceCalculator;
import com.cuongnm.knn.Sample;

import java.util.*;

public class KnnClassifier {
    private final List<? extends Sample> dataSet;
    private int k;

    public KnnClassifier(List<? extends Sample> dataSet, int k) {
        this.dataSet = dataSet;
        this.k = k;
    }

    public String classify(Sample sample){
        Distance[] distances = new Distance[dataSet.size()];
        int index = 0;
        for(Sample localSample: dataSet){
            distances[index] = new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(EuclideanDistanceCalculator.calculate(localSample, sample));
            index++;
        }
        Arrays.sort(distances);
        Map<String, Integer> results = new HashMap<>();
        for(int i = 0; i < k; i++){
            Sample localSample = dataSet.get(distances[i].getIndex());
            String tag = localSample.getTag();
            results.merge(tag, 1, (a,b) -> a+b);
        }
        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
