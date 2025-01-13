package org.example;

import java.util.ArrayList;
import java.util.List;

public class FuzzyEngine {
    private FuzzyVariable distance;
    private FuzzyVariable relativeSpeed;
    private FuzzyVariable trafficIntensity;
    private FuzzyVariable acceleration;

    public FuzzyEngine() {
        // Define fuzzy variables
        distance = new FuzzyVariable("Distance", 0, 120);
        relativeSpeed = new FuzzyVariable("RelativeSpeed", -100, 100);
        trafficIntensity = new FuzzyVariable("TrafficIntensity", 0, 10);
        acceleration = new FuzzyVariable("Acceleration", -5, 5);
    }

    private double fuzzifyDistance(double distanceValue, String category) {
        if (category.equals("Close")) {
            return Math.max(0, Math.min(1, (30 - distanceValue) / 30));
        } else if (category.equals("Medium")) {
            return Math.max(0, Math.min(Math.min((distanceValue - 30) / 30, (90 - distanceValue) / 30), 1));
        } else if (category.equals("Far")) {
            return Math.max(0, Math.min(1, (distanceValue - 90) / 30));
        }
        return 0;
    }

    private double fuzzifyRelativeSpeed(double speedValue, String category) {
        if (category.equals("Negative")) {
            return Math.max(0, Math.min(1, (-speedValue) / 100));
        } else if (category.equals("Zero")) {
            return Math.max(0, Math.min(1, 1 - Math.abs(speedValue) / 50));
        } else if (category.equals("Positive")) {
            return Math.max(0, Math.min(1, speedValue / 100));
        }
        return 0;
    }

    private double fuzzifyTrafficIntensity(double intensityValue, String category) {
        if (category.equals("Low")) {
            return Math.max(0, Math.min(1, (3 - intensityValue) / 3));
        } else if (category.equals("Medium")) {
            return Math.max(0, Math.min(Math.min((intensityValue - 2) / 3, (8 - intensityValue) / 3), 1));
        } else if (category.equals("High")) {
            return Math.max(0, Math.min(1, (intensityValue - 7) / 3));
        }
        return 0;
    }


    public double compute(double distanceValue, double relativeSpeedValue, double trafficIntensityValue) {
        // Fuzzify inputs
        double close = fuzzifyDistance(distanceValue, "Close");
        double medium = fuzzifyDistance(distanceValue, "Medium");
        double far = fuzzifyDistance(distanceValue, "Far");

        double negativeSpeed = fuzzifyRelativeSpeed(relativeSpeedValue, "Negative");
        double zeroSpeed = fuzzifyRelativeSpeed(relativeSpeedValue, "Zero");
        double positiveSpeed = fuzzifyRelativeSpeed(relativeSpeedValue, "Positive");

        double lowTraffic = fuzzifyTrafficIntensity(trafficIntensityValue, "Low");
        double mediumTraffic = fuzzifyTrafficIntensity(trafficIntensityValue, "Medium");
        double highTraffic = fuzzifyTrafficIntensity(trafficIntensityValue, "High");

        // Apply rules
        List<Double> ruleResults = new ArrayList<>();
        ruleResults.add(Math.min(close, negativeSpeed) * -4.0); // Strong deceleration
        ruleResults.add(Math.min(far, positiveSpeed) * 4.0); // Strong acceleration
        ruleResults.add(highTraffic * -1.0); // Slight deceleration
        ruleResults.add(lowTraffic * 1.0); // Slight acceleration
        ruleResults.add(Math.min(medium, zeroSpeed) * 0.0); // Maintain speed

        // Aggregate results (weighted average)
        double numerator = 0;
        double denominator = 0;

        for (double result : ruleResults) {
            numerator += Math.abs(result) * result;
            denominator += Math.abs(result);
        }

        return (denominator != 0) ? numerator / denominator : 0.0;
    }
}
