package com.cuongnm.knn.individualparallel;

import com.cuongnm.knn.Sample;
import com.cuongnm.knn.Distance;
import com.cuongnm.knn.EuclideanDistanceCalculator;

import java.util.concurrent.CountDownLatch;

public class IndividualDistanceTask implements Runnable{
    private final Distance[] distances;
    private final int index;
    private final Sample localSample;
    private final Sample sample;
    private final CountDownLatch countDownLatch;

    public IndividualDistanceTask(Distance[] distances, int index, Sample localSample, Sample sample, CountDownLatch countDownLatch) {
        this.distances = distances;
        this.index = index;
        this.localSample = localSample;
        this.sample = sample;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        distances[index] = new Distance();
        distances[index].setIndex(index);
        distances[index].setDistance(EuclideanDistanceCalculator.calculate(localSample, sample));
        countDownLatch.countDown();
    }
}
