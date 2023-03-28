package com.cuongnm.basic.filesearch;

public class Result {
    private Boolean found;
    private String path;

    public Result(Boolean found, String path) {
        this.found = found;
        this.path = path;
    }

    public Boolean getFound() {
        return found;
    }

    public void setFound(Boolean found) {
        this.found = found;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
