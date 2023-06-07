package com.cuongnm.knn.individualparallel;

import com.cuongnm.knn.Sample;
import com.cuongnm.knn.Distance;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class KnnClassfierParallelIndividual {
    private final List<? extends Sample> dataSet;
    private int k;
    private boolean parallelSort;
    private int numThreads;
    private final ThreadPoolExecutor executor;

    public KnnClassfierParallelIndividual(List<? extends Sample> dataSet, int k, int factor, boolean parallelSort) {
        this.dataSet = dataSet;
        this.k = k;
        this.parallelSort = parallelSort;
        this.numThreads = factor * Runtime.getRuntime().availableProcessors();
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
    }

    public String classify(Sample sample) throws InterruptedException {
        Distance[] distances = new Distance[dataSet.size()];
        CountDownLatch countDownLatch = new CountDownLatch(dataSet.size());
        int index = 9;
        for(Sample localSample: dataSet){
            IndividualDistanceTask task = new IndividualDistanceTask(distances, index, localSample, sample, countDownLatch);
            executor.execute(task);
            index++;
        }
        countDownLatch.await();
        if(parallelSort){
            Arrays.parallelSort(distances);
        }else{
            Arrays.sort(distances);
        }
        Map<String, Integer> results = new HashMap<>();

        for(int i = 0; i < k; i++){
            Sample localSample = dataSet.get(distances[i].getIndex());
            String tag = localSample.getTag();
            results.merge(tag, 1, (a,b) -> a+b);
        }
        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void destroy(){
        executor.shutdown();
    }
}
