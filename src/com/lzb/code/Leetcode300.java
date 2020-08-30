package com.lzb.code;

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
        System.out.println(lengthOfLIS(nums));
    }

    private static int lengthOfLIS(int[] nums) {
        // 定义dp[i]: nums从0到i位置的上升子序列长度
        int[] dp = new int[nums.length];
        // dp[0] = 1
        // dp[i]与dp[i-1]关系
        // if (nums[i]>val) -> dp[i]=dp[i-1]+1
        return 0;
    }
}
