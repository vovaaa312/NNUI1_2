package org.example;

public class FuzzyVariable {
    private String name;
    private double minValue;
    private double maxValue;

    public FuzzyVariable(String name, double minValue, double maxValue) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getName() {
        return name;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }
}

