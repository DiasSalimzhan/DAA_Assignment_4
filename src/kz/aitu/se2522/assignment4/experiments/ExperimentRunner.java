package kz.aitu.se2522.assignment4.experiments;

import kz.aitu.se2522.assignment4.algorithms.*;
import java.util.Random;

public class ExperimentRunner {
    // Fixed random seed and consistent alphabet[cite: 2]
    private static final Random random = new Random(42);

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(4))); // Small alphabet increases match chance
        }
        return sb.toString();
    }

    public static void runExperiments() {
        runDPExperiments();
        runStringMatchingExperiments();
        runKasaiExperiments();
    }

    private static void runDPExperiments() {
        int[] sizes = {100, 500, 1000}; // Input sizes[cite: 2]
        System.out.println("\n--- DP Experiments Execution Time (Average of 5 runs) ---");

        for (int size : sizes) {
            String s1 = generateRandomString(size);
            String s2 = generateRandomString(size);
            long lcsTotal = 0, editTotal = 0;

            for (int i = 0; i < 5; i++) {
                long start = System.nanoTime();
                DynamicProgramming.lcs(s1, s2);
                lcsTotal += (System.nanoTime() - start);

                start = System.nanoTime();
                DynamicProgramming.editDistance(s1, s2);
                editTotal += (System.nanoTime() - start);
            }
            System.out.printf("Size %d: LCS = %d ns | Edit Dist = %d ns\n", size, lcsTotal / 5, editTotal / 5);
        }
    }

    private static void runStringMatchingExperiments() {
        int[] sizes = {10000, 50000, 100000}; // Input sizes[cite: 2]
        System.out.println("\n--- String Matching Experiments (Average of 5 runs) ---");

        for (int size : sizes) {
            String text = generateRandomString(size);
            String pattern = generateRandomString(10); // Small pattern
            long naiveTotal = 0, kmpTotal = 0, rkTotal = 0;

            for (int i = 0; i < 5; i++) {
                long start = System.nanoTime();
                StringMatching.naiveSearch(text, pattern);
                naiveTotal += (System.nanoTime() - start);

                start = System.nanoTime();
                StringMatching.kmpSearch(text, pattern);
                kmpTotal += (System.nanoTime() - start);

                start = System.nanoTime();
                StringMatching.rabinKarpSearch(text, pattern);
                rkTotal += (System.nanoTime() - start);
            }
            System.out.printf("Text %d: Naive = %d ns | KMP = %d ns | Rabin-Karp = %d ns\n",
                    size, naiveTotal / 5, kmpTotal / 5, rkTotal / 5);
        }
    }

    private static void runKasaiExperiments() {
        int[] sizes = {1000, 5000, 10000}; // Input sizes[cite: 2]
        System.out.println("\n--- Kasai Algorithm Experiments (Average of 5 runs) ---");

        for (int size : sizes) {
            String text = generateRandomString(size);
            int[] sa = AdvancedString.buildSimpleSuffixArray(text); // Outside timed region[cite: 2]
            long kasaiTotal = 0;

            for (int i = 0; i < 5; i++) {
                long start = System.nanoTime(); // Use System.nanoTime() for timing[cite: 2]
                AdvancedString.kasaiAlgorithm(text, sa);
                kasaiTotal += (System.nanoTime() - start);
            }
            System.out.printf("String %d: Kasai = %d ns\n", size, kasaiTotal / 5);
        }
    }
}