package com.lzb.code;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 */
public class Leetcode55 {
    public static boolean canJump(int[] nums) {
        // canJump: nums[i] >= nums.length-1-i
        // treeNode interOrder
        if (nums == null || nums.length == 0) {
            return true;
        }
        int n = nums.length;
        int rightMost = 0;
        for (int i = 0; i < n-1; i++) {
            if (i > rightMost) {
                // IMPORTANT
                return false;
            }
            rightMost = Math.max(rightMost, i+nums[i]);
            if (rightMost >= n-1) {
                return true;
            }
        }
        return false;

    }

//    private static boolean canJumpHelper(int[] nums, int finalNum) {
//        if (nums[0] >= finalNum) {
//            return true;
//        }
//        for (int i = 1; i < finalNum; i++) {
//            if (i+nums[i] >= finalNum && canJumpHelper(nums, i)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
