package iie.cloud.dp;

import iie.cloud.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wangwenan
 * @data 2018/8/29 16:38
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
[7],
[2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
[2,2,2,2],
[2,3,3],
[3,5]
]
 */
public class _39_CombinationSum {

    public static void main(String[] args) {
        int[] can = {2, 3, 6, 7};
        int tar = 7;
        combinationSum(can, tar);
    }

    /**
     * DP实现，
     * 二维数组dp[i][j](0<=i<=weights.length,0<=j<=SIZE)表示在最大容量为j，可放入容量是weights[0..i-1]的情况下所有放入可能，初始dp[0][0] = 1, 可以分为两种情况
     *  1. 当前背包最大容量小于物品的重量，可容纳的最大重量是 dp[i-1][j]
     *  2. 当前背包最大容量小于物品重量，可容纳的最大重量是   dp[i-1][j] + dp[i][j-weights[i-1]
     * 二维类似实现见iie/cloud/dp/backpack.java
     * 这里使用一维数组dp_1[]保存数量，同时使用另一个数组保存组合
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = candidates.length;
        if(target <= 0) return res;
//        Arrays.sort(candidates);
//        PrintUtil.printIntArray(candidates);
        int[] dp_1 = new int[target+1];
        dp_1[0] = 1;
        List<ArrayList<List<Integer>>> all_res = new ArrayList<ArrayList<List<Integer>>>();
        for(int i = 0; i < target+1; i++) {
            all_res.add(new ArrayList<List<Integer>>());
        }

        for(int i = 0; i < len; i++) {
            for(int j = 1; j <= target; j++) {
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

//        PrintUtil.printIntArray(dp_1);
//        for(List<Integer> l : all_res.get(target)) {
//            PrintUtil.printIntList(l);
//        }

        return all_res.get(target);
    }

}
