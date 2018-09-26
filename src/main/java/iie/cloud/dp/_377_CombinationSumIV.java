package iie.cloud.dp;

import iie.cloud.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wangwenan
 * @data 2018/8/29 16:38
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
 */
public class _377_CombinationSumIV {

    public static void main(String[] args) {
        int[] can = {1, 2, 3};
//        int[] can = {2,5,1,1,2,3,3,3,1,2,2};
        int tar = 4;
//        int tar = 5;
        System.out.println(combinationSum(can, tar));
    }

    /**
     * DP实现，
     * 二维数组dp[i][j](0<=i<=weights.length,0<=j<=SIZE)表示在最大容量为j，可放入容量是weights[0..i-1]的情况下所有放入可能，初始dp[0][0] = 1, 可以分为两种情况
     *  1. 当前背包最大容量小于物品的重量，可容纳的最大重量是 dp[i-1][j]
     *  2. 当前背包最大容量小于物品重量，可容纳的最大重量是   dp[i-1][j] + dp[weights.length][j-weights[i-1] , 这里若认为只是顺序不同但是相同的结果即求组合数，则最大重量为 dp[i-1][j] + dp[i][j-weights[i-1]
     *  该方法外循环是容量SIZE，内循环是不同的容量
     * @param candidates
     * @param target
     * @return
     */
    public static int combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if(target <= 0) return 0;
        int[][] dp = new int[len+1][target+1];
        for(int i = 0; i < len+1; i++) {
            dp[i][0] = 1;
        }
        List<ArrayList<List<Integer>>> pre_tar_list = new ArrayList<ArrayList<List<Integer>>>();
        for(int i = 0; i < target+1; i++) {
            pre_tar_list.add(new ArrayList<List<Integer>>());
        }


        for(int j = 1; j < target+1; j++) {
            for(int i = 1; i < len+1; i++) {
                if(j < candidates[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    ArrayList<List<Integer>> tmp = new ArrayList<List<Integer>>();
                    for(List<Integer> l: pre_tar_list.get(j-candidates[i-1])) {
                        if(l.size() > 0) {
                            tmp.add(new ArrayList<Integer>(l));
                        }
                    }
                    if(tmp.size() != 0) {
                        for(List<Integer> ll: tmp) {
                            ll.add(candidates[i-1]);
                        }
                    } else if (dp[i][j-candidates[i-1]] != 0) {
                        tmp.add(new ArrayList<Integer>(Arrays.asList(candidates[i-1])));
                    }

                    pre_tar_list.get(j).addAll(tmp);
                    dp[i][j] = dp[i-1][j] + dp[len][j-candidates[i-1]];
                }
            }
        }

//        for(int[] ii: dp) {
//            PrintUtil.printIntArray(ii);
//        }
//
//        for(List<Integer> l : pre_tar_list.get(target)) {
//            PrintUtil.printIntList(l);
//        }
//        System.out.println();

        return dp[len][target];
    }

}
