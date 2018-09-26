package iie.cloud.dp;

import iie.cloud.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wangwenan
 * @data 2018/8/29 16:38
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class _216_CombinationSumIII {

    public static void main(String[] args) {
        int k = 3;
        int n = 7;
        combinationSum(k, n);
    }

    /**
     * DP实现，
     * 二维数组dp[i][j](0<=i<=weights.length,0<=j<=SIZE)表示在最大容量为j，可放入容量是weights[0..i-1]的情况下所有放入可能，初始dp[0][0] = 1, 可以分为两种情况
     *  1. 当前背包最大容量小于物品的重量，可容纳的最大重量是 dp[i-1][j]
     *  2. 当前背包最大容量小于物品重量，可容纳的最大重量是   dp[i-1][j] + dp[i-1][j-weights[i-1]
     * 二维类似实现见iie/cloud/dp/backpack.java
     * 这里使用一维数组dp_1[]保存数量，同时使用另一个数组保存组合
     * @param k
     * @param n
     * @return
     */
    public static List<List<Integer>> combinationSum(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(n < (1+k)*k/2) return res;
        int[] candidates = new int[n];
        for(int i = 1; i <= n; i++) {
            candidates[i-1] = i;
        }
        int len = Math.min(9, n);
        int[] dp_1 = new int[n+1];
        dp_1[0] = 1;
        List<ArrayList<List<Integer>>> all_res = new ArrayList<ArrayList<List<Integer>>>();
        for(int i = 0; i < n+1; i++) {
            all_res.add(new ArrayList<List<Integer>>());
        }

        for(int i = 0; i < len; i++) {
            for(int j = n; j > 0; j--) {
                if(j >= candidates[i]) {
                    ArrayList<List<Integer>> tmp = new ArrayList<List<Integer>>();
                    for(List<Integer> l: all_res.get(j-candidates[i])) {
                        tmp.add(new ArrayList<Integer>(l));
                    }
                    if(tmp.size() != 0) {
                        for(List<Integer> ll: tmp) {
                            ll.add(candidates[i]);
                        }
                    } else if (dp_1[j-candidates[i]] != 0){
                        tmp.add(new ArrayList<Integer>(Arrays.asList(candidates[i])));
                    }

                    all_res.get(j).addAll(tmp);
                    dp_1[j] = dp_1[j] + dp_1[j-candidates[i]];
                }
            }
        }

        for(List<Integer> l : all_res.get(n)) {
            PrintUtil.printIntList(l);
        }
        System.out.println();

        // 取出长度不为k的元素
        ArrayList<List<Integer>> target_list = all_res.get(n);
        for(int i = 0; i < target_list.size(); i++) {
            if(target_list.get(i).size() == k) res.add(target_list.get(i));
        }

        for(List<Integer> l : res) {
            PrintUtil.printIntList(l);
        }

        return res;
    }

}
