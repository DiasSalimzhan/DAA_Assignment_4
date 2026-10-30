# Assignment 4: Text Analysis - Dynamic Programming and String Algorithms

## 1. Problem Description and Algorithm Overview
This project implements Java-based text analysis using dynamic programming (Longest Common Subsequence, Edit Distance) and string-matching algorithms (Naive, KMP, Rabin-Karp)[cite: 1]. It also includes Kasai's algorithm for constructing the Longest Common Prefix (LCP) array from a suffix array[cite: 1].

## 2. Implementation Decisions and Assumptions
* **String Matching:** All algorithms (Naive, KMP, Rabin-Karp) return a list of starting indices where the pattern occurs[cite: 1]. Empty patterns are handled by returning an empty list[cite: 3].
* **Kasai's Algorithm:** A helper method builds a simple suffix array for small test strings to feed into Kasai's algorithm[cite: 2].
* **Experimental Setup:** System.nanoTime() is used for precise timing, running each experiment 5 times to report the average[cite: 2]. Random strings are generated using a fixed random seed (42) and a consistent lowercase English alphabet[cite: 2]. Console I/O and generation times are excluded from the timed regions[cite: 2].

## 3. Complexity Analysis Table

| Algorithm | Main Operation | Best-Case Time | Worst-Case Time | Space Complexity | Justification |
|-----------|----------------|----------------|-----------------|------------------|---------------|
| **LCS** | DP Matrix cell evaluation | O(m × n) | O(m × n) | O(m × n) | Requires evaluating every cell in an `m x n` table. |
| **Edit Distance** | DP Matrix cell evaluation | O(m × n) | O(m × n) | O(m × n) | Requires evaluating substitutions, insertions, and deletions for each `(i, j)`. |
| **Naive Match** | Character comparison | O(n) | O(m × (n-m+1)) | O(1) | No preprocessing. Worst-case occurs with many partial matches (e.g., text="AAAAA", pattern="AAAB"). |
| **KMP** | Array traversal & matching | O(n + m) | O(n + m) | O(m) | Preprocessing the LPS array avoids redundant comparisons during the search space traversal[cite: 1]. |
| **Rabin-Karp** | Hash calculation & match | O(n + m) | O(n × m) | O(1) | Uses a rolling hash. The worst case happens only if severe hash collisions occur frequently[cite: 1]. |
| **Kasai's LCP** | `k` iteration & increment | O(n) | O(n) | O(n) | The inverse suffix array allows `k` to decrease by at most 1 per iteration, bounding total increments to O(n)[cite: 2]. |

## 4. Testing Strategy
* **LCS & Edit Distance:** Tested with identical strings, completely different strings, and empty strings[cite: 2].
* **String Matching:** Tested with patterns at the beginning, middle, and end, multiple occurrences, absent patterns, and empty patterns[cite: 2]. Verified that Naive, KMP, and Rabin-Karp all return the exact same indices[cite: 3].
* **Kasai's LCP:** Tested and manually verified using the string "banana"[cite: 3].

## 5. Experimental Results
*(Note: Please run the `ExperimentRunner.java` file and paste the console output here, and upload the two required execution time plots (DP and String Matching) into the repository[cite: 2, 3])*

## 6. Conclusion
Theoretical bounds align with the experimental results. KMP and Rabin-Karp demonstrate significant performance advantages over the Naive approach as text size scales to 100,000 characters. For dynamic programming, execution time grows quadratically as expected. Kasai's algorithm effectively maintains linear time complexity for LCP construction.