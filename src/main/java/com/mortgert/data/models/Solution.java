package com.mortgert.data.models;

public class Solution {

    private long timeStarted;

    private long timeSubmitted;

    private String solutionString;

    private String problemID;

    public long getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(long timeStarted) {
        this.timeStarted = timeStarted;
    }

    public long getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted(long timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public String getSolutionString() {
        return solutionString;
    }

    public void setSolutionString(String solutionString) {
        this.solutionString = solutionString;
    }

    public String getProblemID() {
        return problemID;
    }

    public void setProblemID(String problemID) {
        this.problemID = problemID;
    }
}
