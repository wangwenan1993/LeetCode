package iie.cloud.dp;

import iie.cloud.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wangwenan
 * @data 2018/8/29 16:38
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
[1, 7],
[1, 2, 5],
[2, 6],
[1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
[1,2,2],
[5]
]
 */
public class _40_CombinationSumII {

    public static void main(String[] args) {
        int[] can = {10,1,2,7,6,1,5};
//        int[] can = {2,5,1,1,2,3,3,3,1,2,2};
        int tar = 8;
//        int tar = 5;
        combinationSum(can, tar);
    }

    /**
     * DP实现，
     * 二维数组dp[i][j](0<=i<=weights.length,0<=j<=SIZE)表示在最大容量为j，可放入容量是weights[0..i-1]的情况下所有放入可能，初始dp[0][0] = 1, 可以分为两种情况
     *  1. 当前背包最大容量小于物品的重量，可容纳的最大重量是 dp[i-1][j]
     *  2. 当前背包最大容量小于物品重量，可容纳的最大重量是   dp[i-1][j] + dp[i-1][j-weights[i-1]
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
        Arrays.sort(candidates);
//        PrintUtil.printIntArray(candidates);
        int[] dp_1 = new int[target+1];
        dp_1[0] = 1;
        List<ArrayList<List<Integer>>> all_res = new ArrayList<ArrayList<List<Integer>>>();
        for(int i = 0; i < target+1; i++) {
            all_res.add(new ArrayList<List<Integer>>());
        }

        for(int i = 0; i < len; i++) {
            for(int j = target; j > 0; j--) {
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

        PrintUtil.printIntArray(dp_1);
        for(List<Integer> l : all_res.get(target)) {
            PrintUtil.printIntList(l);
        }
        System.out.println();

        // 去掉重复元素
        ArrayList<List<Integer>> target_list = all_res.get(target);
        for(int i = 0; i < target_list.size(); i++) {
            if(!res.contains(target_list.get(i))) res.add(target_list.get(i));
        }

        for(List<Integer> l : res) {
            PrintUtil.printIntList(l);
        }

        return res;
    }

}
