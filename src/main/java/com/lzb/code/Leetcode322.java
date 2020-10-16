package com.lzb.code;

public class Leetcode322 {
    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 例如：
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     */
    private static StringBuilder nums = new StringBuilder();

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5, 10, 20, 50, 100, 200};
        System.out.println(coinChange(coins, 79864));
        System.out.println(nums);
    }

    public static int coinChange(int[] coins, int mount) {
        // if assert <expression> is true, task goes on
        // if assert <expression> is false, task throws exception
        assert coins != null && coins.length > 0 : "coins can not be null";
        int res = 0;
        for (int i = coins.length-1; i >= 0; i--) {
            if (mount >= coins[i]) {
                nums.append(coins[i]).append("*").append(mount/coins[i]).append("  ");
                res += mount/coins[i];
                mount = mount%coins[i];
            }
        }
        return res;
    }
}
