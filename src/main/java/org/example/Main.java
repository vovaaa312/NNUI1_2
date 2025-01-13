package org.example;

public class Main {
    public static void main(String[] args) {
        // Initialize fuzzy engine
        FuzzyEngine engine = new FuzzyEngine();

        // Define test cases
        double[][] testCases = {
                {10, -50, 8},  // Case 1: Close distance, negative speed, high traffic
                {100, 20, 2},  // Case 2: Far distance, positive speed, low traffic
                {50, 0, 5},    // Case 3: Medium distance, zero speed, medium traffic
                {20, -70, 9},  // Case 4: Very close distance, high negative speed, high traffic
                {80, 30, 1} ,   // Case 5: Far distance, positive speed, minimal traffic
                {120, -100, 10},  // Extreme case: far distance, high closing speed, high traffic
                {0, 100, 0}       // Extreme case: very close, high relative speed, low traffic
        };

        // Process each test case
        System.out.println("Distance\tRelSpeed\tTrafficInt\tAcceleration");
        for (double[] testCase : testCases) {

            double distance = testCase[0];
            double relativeSpeed = testCase[1];
            double trafficIntensity = testCase[2];
            double acceleration = engine.compute(distance, relativeSpeed, trafficIntensity);

            System.out.printf("%.2f\t\t%.2f\t\t%.2f\t\t%.2f\n", distance, relativeSpeed, trafficIntensity, acceleration);
        }
    }
}
