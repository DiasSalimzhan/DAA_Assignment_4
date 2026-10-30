package kz.aitu.se2522.assignment4.tests;

import kz.aitu.se2522.assignment4.algorithms.*;
import java.util.Arrays;
import java.util.List;

public class AlgorithmTests {

    public static void runAllTests() {
        testDP();
        testStringMatching();
        testKasai();
    }

    private static void testDP() {
        System.out.println("--- Testing DP Algorithms ---");
        // LCS Tests[cite: 2]
        assert DynamicProgramming.lcs("abcde", "ace") == 3;
        assert DynamicProgramming.lcs("abc", "abc") == 3; // Identical
        assert DynamicProgramming.lcs("abc", "def") == 0; // Completely different
        assert DynamicProgramming.lcs("", "abc") == 0;    // Empty string

        // Edit Distance Tests[cite: 2]
        assert DynamicProgramming.editDistance("horse", "ros") == 3;
        assert DynamicProgramming.editDistance("abc", "abc") == 0; // Identical
        assert DynamicProgramming.editDistance("", "abc") == 3;    // Empty string
        System.out.println("DP Tests Passed!");
    }

    private static void testStringMatching() {
        System.out.println("--- Testing String Matching Algorithms ---");
        String text = "abracadabra";

        // Pattern at beginning, middle, end, multiple, absent, empty[cite: 2]
        String[] patterns = {"abr", "cad", "bra", "a", "xyz", "", "abracadabra_long"};

        for (String pat : patterns) {
            List<Integer> naiveRes = StringMatching.naiveSearch(text, pat);
            List<Integer> kmpRes = StringMatching.kmpSearch(text, pat);
            List<Integer> rabinRes = StringMatching.rabinKarpSearch(text, pat);

            // Verify all return the same list[cite: 3]
            assert naiveRes.equals(kmpRes) && kmpRes.equals(rabinRes) : "Mismatch found for pattern: " + pat;
        }
        System.out.println("String Matching Tests Passed!");
    }

    private static void testKasai() {
        System.out.println("--- Testing Kasai Algorithm ---");
        String text = "banana"; // Short string manually verified[cite: 2, 3]
        int[] sa = AdvancedString.buildSimpleSuffixArray(text);
        int[] expectedLCP = {1, 3, 0, 0, 2, 0}; // Correct LCP for "banana"
        int[] lcp = AdvancedString.kasaiAlgorithm(text, sa);

        assert Arrays.equals(lcp, expectedLCP) : "Kasai LCP incorrect";
        System.out.println("Kasai Tests Passed!");
    }
}