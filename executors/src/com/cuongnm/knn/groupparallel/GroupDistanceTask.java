package com.cuongnm.knn.groupparallel;

import com.cuongnm.knn.Distance;
import com.cuongnm.knn.EuclideanDistanceCalculator;
import com.cuongnm.knn.Sample;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GroupDistanceTask implements Runnable{
    private final Distance[] distances;
    private final int startIndex;
    private final int endIndex;
    private final Sample sample;
    private final List<? extends Sample> dataSet;
    private final CountDownLatch countDownLatch;

    public GroupDistanceTask(Distance[] distances, int startIndex, int endIndex, Sample sample, List<? extends Sample> dataSet, CountDownLatch countDownLatch) {
        this.distances = distances;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.sample = sample;
        this.dataSet = dataSet;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for(int index = startIndex; index < endIndex; index++){
            Sample localSample = dataSet.get(index);
            distances[index] = new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(EuclideanDistanceCalculator.calculate(localSample, sample));
        }
        countDownLatch.countDown();
    }
}
