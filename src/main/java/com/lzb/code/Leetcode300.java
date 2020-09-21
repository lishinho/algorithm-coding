package com.lzb.code;

import java.util.Arrays;

public class Leetcode300 {
    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * 例如
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     */
    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("result is " + lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        // 定义dp[i]: nums从0到i位置的上升子序列长度， 在大循环中动态更新
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
