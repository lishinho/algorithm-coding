package com.lzb.code;

public class Leetcode516 {

    // f[i][j] implies the result in substring(from s[i] to s[j])
    // if s[i] == s[j] -> f[i][j] = f[i+1][j-1]+2
    // else f[i][j] = max(f[i+1][j], f[i][j-1])
    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        // res is dp[0][n-1]
        for (int i = n-1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] +2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
