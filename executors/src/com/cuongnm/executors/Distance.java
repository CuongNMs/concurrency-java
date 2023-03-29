package com.cuongnm.executors;

public class Distance implements Comparable<Distance>{
    private Integer index;
    private Double distance;

    public Distance() {
    }

    public Distance(Integer index, Double distance) {
        this.index = index;
        this.distance = distance;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Distance o) {
        if(this.distance < o.distance){
            return -1;
        }else if(this.distance > o.distance){
            return 1;
        }else{
            return 0;
        }
    }
}
