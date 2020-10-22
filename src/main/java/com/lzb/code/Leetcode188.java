package com.lzb.code;

import static java.lang.Integer.max;

public class Leetcode188 {
    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 输入: [2,4,1], k = 2
     * 输出: 2
     * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，
     * 在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2。
     */

    /*
    base case：
    dp[-1][k][0] = dp[i][0][0] = 0
    dp[-1][k][1] = dp[i][0][1] = -infinity

    状态转移方程：
    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
    第i天交易k次，现在的状态是0未持有；1持有
     */
    public static int maxProfit_k_any(int max_k, int[] prices) {
        int n = prices.length;
        // when k is above n/2, we can see max_k as infinitive
        if (max_k > n / 2) {
            return maxProfit_k_inf(prices);
        }

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i == 0) {
                    /* handle base case */
                    dp[i][k][0] = 0;
                    dp[i][k][1] = Integer.MIN_VALUE;
                } else {
                    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
                }
            }
        }
        return dp[n - 1][max_k][0];
    }

    private static int maxProfit_k_inf(int[] prices) {
        // dp[i][0] the i day doesn't have stock = max(dp[i-1][0], dp[i-1][1]+prices[i])
        // dp[i][1] the i day has stock = max(dp[i-1][1],dp[i-1][0]-prices[i])
        // the result is dp[n][0]
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }
}
