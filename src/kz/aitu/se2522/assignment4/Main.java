package kz.aitu.se2522.assignment4;

import kz.aitu.se2522.assignment4.tests.AlgorithmTests;
import kz.aitu.se2522.assignment4.experiments.ExperimentRunner;

public class Main {
    public static void main(String[] args) {
        // Assertion функциясын қосу үшін JVM аргументіне -ea (enable assertions) қоюды ұмытпаңыз.
        System.out.println("Starting Assignment 4...");

        AlgorithmTests.runAllTests();
        ExperimentRunner.runExperiments();

        System.out.println("All operations completed successfully.");
    }
}