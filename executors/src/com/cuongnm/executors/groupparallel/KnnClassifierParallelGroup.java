package com.cuongnm.executors.groupparallel;

import com.cuongnm.executors.Distance;
import com.cuongnm.executors.Sample;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

public class KnnClassifierParallelGroup {
    private final List<? extends Sample> dataSet;
    private int k;
    private boolean parallelSort;
    private int numThreads;
    private final ThreadPoolExecutor executor;

    public KnnClassifierParallelGroup(List<? extends Sample> dataSet, int k, boolean parallelSort, int numThreads, ThreadPoolExecutor executor) {
        this.dataSet = dataSet;
        this.k = k;
        this.parallelSort = parallelSort;
        this.numThreads = numThreads;
        this.executor = executor;
    }

    public String classify(Sample sample) throws InterruptedException {
        Distance[] distances = new Distance[dataSet.size()];
        CountDownLatch countDownLatch = new CountDownLatch(numThreads);
        int length = dataSet.size()/numThreads;
        int startIndex =0;
        int endIndex = length;
        for(int i =0; i < numThreads; i++){
            GroupDistanceTask task = new GroupDistanceTask(distances, startIndex, endIndex, sample, dataSet, countDownLatch);
            startIndex = endIndex;
            if(i<numThreads - 2){
                endIndex = endIndex + length;
            }else{
                endIndex = dataSet.size();
            }
            executor.execute(task);
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
}
