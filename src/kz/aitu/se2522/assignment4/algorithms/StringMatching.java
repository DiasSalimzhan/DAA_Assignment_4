package kz.aitu.se2522.assignment4.algorithms;

import java.util.ArrayList;
import java.util.List;

public class StringMatching {

    // Naive string matching baseline[cite: 1]
    public static List<Integer> naiveSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (pattern.isEmpty() || text.length() < pattern.length()) return result;

        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) break;
            }
            if (j == m) result.add(i);
        }
        return result;
    }

    // Knuth-Morris-Pratt (KMP)[cite: 1]
    public static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (pattern.isEmpty() || text.length() < pattern.length()) return result;

        int m = pattern.length();
        int n = text.length();
        int[] lps = new int[m];
        computeLPSArray(pattern, m, lps);

        int i = 0, j = 0;
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++; i++;
            }
            if (j == m) {
                result.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) j = lps[j - 1];
                else i = i + 1;
            }
        }
        return result;
    }

    private static void computeLPSArray(String pat, int m, int[] lps) {
        int len = 0, i = 1;
        lps[0] = 0;
        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++; lps[i] = len; i++;
            } else {
                if (len != 0) len = lps[len - 1];
                else { lps[i] = len; i++; }
            }
        }
    }

    // Rabin-Karp Search[cite: 1]
    public static List<Integer> rabinKarpSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (pattern.isEmpty() || text.length() < pattern.length()) return result;

        int d = 256; // Alphabet size
        int q = 101; // A prime number
        int m = pattern.length();
        int n = text.length();
        int i, j;
        int p = 0, t = 0, h = 1;

        for (i = 0; i < m - 1; i++) h = (h * d) % q;
        for (i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        for (i = 0; i <= n - m; i++) {
            if (p == t) { // Check for hash collision[cite: 1]
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) break;
                }
                if (j == m) result.add(i);
            }
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;
                if (t < 0) t = (t + q);
            }
        }
        return result;
    }
}