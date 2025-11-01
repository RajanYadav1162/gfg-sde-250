package com.codewith_rajan.Array.EASY;
import java.util.*;

class Q1 {
}
// Solution 1: Finds repeating and missing numbers using XOR bit manipulation.
// Time Complexity: O(n), Space Complexity: O(1)
class Solution {
  ArrayList<Integer> findTwoElement(int arr[]) {
    // XOR all elements and numbers 1 to n
    int num = 0;
    int n = arr.length;
    for (int a : arr) num ^= a;
    for (int i = 1; i <= n; i++) num ^= i;

    // num = missing ^ repeating
    int mask = num & ~(num - 1);

    // Partition into two groups and XOR separately
    int g1 = 0, g2 = 0;
    for (int a : arr) {
      if ((a & mask) != 0) g1 ^= a;
      else g2 ^= a;
    }
    for (int i = 1; i <= n; i++) {
      if ((i & mask) != 0) g1 ^= i;
      else g2 ^= i;
    }

    // Identify which is repeating
    int cnt = 0;
    for (int a : arr) {
      if (a == g1) cnt++;
    }

    if (cnt == 2) {
      return new ArrayList<>(List.of(g1, g2));
    }
    return new ArrayList<>(List.of(g2, g1));
  }

  // Solution 2: Uses boolean array to track seen elements.
  // Time Complexity: O(n), Space Complexity: O(n)
  ArrayList<Integer> findTwoElementUsingSeen(int arr[]) {
    int n = arr.length;
    boolean[] seen = new boolean[n + 1];
    int missing = 0, repeating = 0;

    for (int a : arr) {
      if (seen[a]) {
        repeating = a;
      }
      seen[a] = true;
    }

    for (int i = 1; i <= n; i++) {
      if (!seen[i]) {
        missing = i;
        break;
      }
    }

    return new ArrayList<>(List.of(repeating, missing));
  }
}


