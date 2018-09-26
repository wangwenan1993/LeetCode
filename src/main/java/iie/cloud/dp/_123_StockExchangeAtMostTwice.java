package iie.cloud.dp;

/**
 * @Author wangwenan
 * @data 2018/8/28 9:25
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class _123_StockExchangeAtMostTwice {

    // f[k, ii] represents the max profit up until prices[ii] (Note: NOT ending with prices[ii]) using at most k transactions.
    // f[k, ii] = max(f[k, ii-1], prices[ii] - prices[jj] + f[k-1, jj]) { jj in range of [0, ii-1] }
    //          = max(f[k, ii-1], prices[ii] + max(f[k-1, jj] - prices[jj]))
    // f[0, ii] = 0; 0 times transation makes 0 profit
    // f[k, 0] = 0; if there is only one price data point you can't make any money no matter how many times you can trade
    public int maxProfit(int[] prices) {
        int max_profit = 0;
        if(prices.length <= 0) return max_profit;
        final int K = 2; // 最大可交易次数
        int[][] dp = new int[K+1][prices.length];
        for(int kk = 1; kk <= K; kk++) {
            int tmpMax = dp[kk-1][0] - prices[0];
            for (int ii = 1; ii < prices.length; ii++) {
                dp[kk][ii] = Math.max(dp[kk][ii-1], prices[ii] + tmpMax);
                tmpMax = Math.max(tmpMax, dp[kk-1][ii] - prices[ii]);
                max_profit = Math.max(max_profit, dp[kk][ii]);
            }
        }
        return max_profit;
    }
}
