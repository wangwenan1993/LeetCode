package iie.cloud.dp;

/**
 * @Author wangwenan
 * @data 2018/8/28 9:25
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class _188_StockExchangeAtMostKTimes {

    // f[k, ii] represents the max profit up until prices[ii] (Note: NOT ending with prices[ii]) using at most k transactions.
    // f[k, ii] = max(f[k, ii-1], prices[ii] - prices[jj] + f[k-1, jj]) { jj in range of [0, ii-1] }
    //          = max(f[k, ii-1], prices[ii] + max(f[k-1, jj] - prices[jj]))
    // f[0, ii] = 0; 0 times transation makes 0 profit
    // f[k, 0] = 0; if there is only one price data point you can't make any money no matter how many times you can trade
    public int maxProfit(int k, int[] prices) {
        int max_profit = 0;
        if(prices.length <= 0) return max_profit;
        if(k > prices.length / 2) return quickSolve(prices);   // 处理k特别大时出现TLD
        int[] dp_kk_1_trans = new int[prices.length];   // 存储只交易kk-1次时的收益，防止出现MLD
        for(int kk = 1; kk <= k; kk++) {
            int tmpMax = dp_kk_1_trans[0] - prices[0];
            for (int ii = 1; ii < prices.length; ii++) {
                int curr = Math.max(dp_kk_1_trans[ii-1], prices[ii] + tmpMax);
                tmpMax = Math.max(tmpMax, dp_kk_1_trans[ii] - prices[ii]);
                dp_kk_1_trans[ii] = curr;
                max_profit = Math.max(max_profit, dp_kk_1_trans[ii]);
            }
        }
        return max_profit;
    }

    public static int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}
