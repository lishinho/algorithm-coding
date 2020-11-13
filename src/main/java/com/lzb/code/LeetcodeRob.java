package com.lzb.code;

import com.lzb.struct.TreeNode;

import java.util.Arrays;

public class LeetcodeRob {
    /**
     * Leetcode-198
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     */
    public static int rob(int[] nums) {
        // dp[i][0] 偷到第i家，不可以再偷 = dp[i-1][1] + nums[i]
        // dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0])
        // res = dp[n][1]
        // can be optimised dp[i][0] as a, dp[i][1] as b
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i-1][1] + nums[i-1], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]);
        }
        return Math.max(dp[n][1], dp[n][0]);
    }

    private static int rob1Optimised(int[] nums) {
        // dp[i][0] 偷到第i家，不可以再偷 = dp[i-1][1] + nums[i]
        // dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0])
        // res = dp[n][1]
        // can be optimised dp[i][0] as a, dp[i][1] as b
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int a = 0,b = 0;
        for (int i = 1; i <= n; i++) {
            a = Math.max(b + nums[i-1], a);
            b = Math.max(b, a);
        }
        return Math.max(a, b);
    }

    /**
     * leetcode-213
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
     * 这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
     *
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     *
     */

    public static int rob2(int[] nums) {
        // 把第一种情况分成两个list
        // 一个是nums[0...n-1]
        // 另一个是nums[1...n]取两个最大值
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(rob1Optimised(Arrays.copyOfRange(nums, 0, nums.length-1)),
                rob1Optimised(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    /**
     * Leetcode-337
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
     * 这个地区只有一个入口，我们称之为“根”。
     * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     * 示例 1:
     *
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     *
     */

    public static int rob3(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    // result[0] means do not steal
    // result[1] means steal this node

    private static int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }
}
