package kz.aitu.se2522.assignment4.algorithms;

public class AdvancedString {

    // Kasai's algorithm for LCP array[cite: 1, 2]
    public static int[] kasaiAlgorithm(String text, int[] suffixArray) {
        int n = suffixArray.length;
        int[] lcp = new int[n];
        int[] invSuff = new int[n];

        for (int i = 0; i < n; i++) {
            invSuff[suffixArray[i]] = i;
        }

        int k = 0;
        for (int i = 0; i < n; i++) {
            if (invSuff[i] == n - 1) {
                k = 0;
                continue;
            }
            int j = suffixArray[invSuff[i] + 1];
            while (i + k < n && j + k < n && text.charAt(i + k) == text.charAt(j + k)) {
                k++;
            }
            lcp[invSuff[i]] = k;
            if (k > 0) k--;
        }
        return lcp;
    }

    // Helper method to build simple suffix array for Kasai tests[cite: 2]
    public static int[] buildSimpleSuffixArray(String text) {
        int n = text.length();
        Integer[] suffixes = new Integer[n];
        for (int i = 0; i < n; i++) suffixes[i] = i;

        java.util.Arrays.sort(suffixes, (a, b) -> text.substring(a).compareTo(text.substring(b)));

        int[] sa = new int[n];
        for (int i = 0; i < n; i++) sa[i] = suffixes[i];
        return sa;
    }
}