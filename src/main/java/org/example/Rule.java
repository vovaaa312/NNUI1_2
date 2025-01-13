package org.example;

public class Rule {
    private String condition;
    private String conclusion;

    public Rule(String condition, String conclusion) {
        this.condition = condition;
        this.conclusion = conclusion;
    }

    public String getCondition() {
        return condition;
    }

    public String getConclusion() {
        return conclusion;
    }
}