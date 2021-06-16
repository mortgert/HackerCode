package com.mortgert.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Problem {
    @Id
    @Column(name = "problem_id")
    private int problemID;

    @Column(name = "problem_beginning")
    private String problemBeginning;

    @Column(name = "problem_end")
    private String problemEnd;

    @Column(name = "class_name")
    private String className;

    public String getProblemEnd() {
        return problemEnd;
    }

    public void setProblemEnd(String problemEnd) {
        this.problemEnd = problemEnd;
    }

    public String getProblemBeginning() {
        return problemBeginning;
    }

    public void setProblemBeginning(String problemBeginning) {
        this.problemBeginning = problemBeginning;
    }

    public int getProblemID() {
        return problemID;
    }

    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
