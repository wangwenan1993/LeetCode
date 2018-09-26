package iie.cloud.dp;

/**
 * @Author wangwenan
 * @data 2018/8/28 9:25
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class _714_StockExchangeAnyTimesWithFee {

    /**
     * This problem is just like the other stock problems.
     At given day, we can do 1 out of 4 things:

     buy stock
     hold stock
     do nothing with empty portfolio
     sell stock
     We have 4 arrays with the length of # of the days, recording the max profit at given day if we do given operation.
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        if(prices.length <= 1) return 0;
        int[] buy = new int[prices.length];
        int[] hold = new int[prices.length];
        int[] skip = new int[prices.length];
        int[] sell = new int[prices.length];
        // the moment we buy a stock, our balance should decrease
        buy[0] = 0 - prices[0];
        // assume if we have stock in the first day, we are still in deficit
        hold[0] = 0 - prices[0];
        for(int i = 1; i < prices.length; i++){
            // We can only buy on today if we sold stock
            // or skipped with empty portfolio yesterday
            buy[i] = Math.max(skip[i-1], sell[i-1]) - prices[i];
            // Can only hold if we bought or already holding stock yesterday
            hold[i] = Math.max(buy[i-1], hold[i-1]);
            // Can skip only if we skipped, or sold stock yesterday
            skip[i] = Math.max(skip[i-1], sell[i-1]);
            // Can sell only if we bought, or held stock yesterday
            sell[i] = Math.max(buy[i-1], hold[i-1]) + prices[i] - fee;
        }
        // Get the max of all the 4 actions on the last day.
        int max = Math.max(buy[prices.length - 1], hold[prices.length - 1]);
        max = Math.max(skip[prices.length - 1], max);
        max = Math.max(sell[prices.length - 1], max);
        return Math.max(max, 0);
    }
}
