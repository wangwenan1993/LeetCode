package iie.cloud.leetcode;

/**
 * @Author wangwenan
 * @data 2018/8/29 11:28
 */
public class Backpack {

    public static void main(String[] args) {
        int[] weights = {2, 3, 5, 7};
        int[] values = {1, 5, 2, 4};
        final int SIZE = 10;
        System.out.println("weight: " + maxWeight(weights, SIZE));
        System.out.println("values: " + maxValue(weights, values, SIZE));
        System.out.println("values: " + maxValueWithSame(weights, values, SIZE));

        int[] w = {2, 3, 6, 7};
        System.out.println("counts with same: " + maxCombinationsMatchTargetWithSame(w, 7));

        int[] w1 = {1, 2, 3, 3, 7};
        System.out.println("counts: " + maxCombinationsMatchTarget(w1, 7));
    }

    /**
     * 1.背包可放入的最大质量
     *  Given n items with size Ai, an integer m denotes the size of a backpack.
        How full you can fill this backpack?
     * 二维数组dp[i][j](0<=i<=weights.length,0<=j<=SIZE)表示在最大容量为j，可放入容量是weights[0..i-1]的情况下可放入的最大容量，可以分为两种情况
     *  1. 当前背包最大容量小于物品的重量，可容纳的最大重量是dp[i-1][j]
     *  2. 当前背包最大容量小于物品重量，可容纳的最大重量是max(dp[i-1][j],dp[i-1][j-weights[i-1]+weights[i-1])
     * @param weights
     * @param SIZE
     * @return
     */
    public static int maxWeight(int[] weights, int SIZE) {
        int num = weights.length;
//        int[][] dp = new int[num+1][SIZE+1];
//
//        for(int i = 1; i <= num; i++) {
//
//            for(int j = 1; j <= SIZE; j++) {
//                if(j < weights[i-1]) dp[i][j] = dp[i-1][j];
//                else {
//                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]] + weights[i-1]);
//                }
//            }
//        }
//        return dp[num][SIZE];

        // 只使用一维数组
        int[] dp_1 = new int[SIZE+1];
        for(int i = 0; i < num; i++) {
            for(int j = SIZE; j > 0; j--) {   // 必须是逆序，如果顺序需要两个数组
                if(j >= weights[i]) {
                    dp_1[j] = Math.max(dp_1[j], dp_1[j-weights[i]] + weights[i]);
                }
            }
        }
        return dp_1[SIZE];
    }

    /**
     * 2.背包可放入的最大价值
     *  Given n items with size Ai and value Vi, and a backpack with size m.
        What's the maximum value can you put into the backpack?
     * 二维数组dp[i][j](0<=i<=weights.length,0<=j<=SIZE)表示在最大容量为j，可放入容量是weights[0..i-1]的情况下可放入的最大价值，可以分为两种情况
     *  1. 当前背包最大容量小于物品的重量，可容纳的最大重量是dp[i-1][j]
     *  2. 当前背包最大容量小于物品重量，可容纳的最大重量是max(dp[i-1][j],dp[i-1][j-weights[i-1]+values[i-1])
     * @param weights
     * @param SIZE
     * @return
     */
    public static int maxValue(int[] weights, int[] values, int SIZE) {
        int num = weights.length;
//        int[][] dp = new int[num+1][SIZE+1];
//
//        for(int i = 1; i <= num; i++) {
//
//            for(int j = 1; j <= SIZE; j++) {
//                if(j < weights[i-1]) dp[i][j] = dp[i-1][j];
//                else {
//                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]] + values[i-1]);
//                }
//            }
//        }
//        return dp[num][SIZE];

        // 只使用一维数组
        int[] dp_1 = new int[SIZE+1];
        for(int i = 0; i < num; i++) {
            for(int j = SIZE; j > 0; j--) {   // 必须是逆序，如果顺序需要两个数组
                if(j >= weights[i]) {
                    dp_1[j] = Math.max(dp_1[j], dp_1[j-weights[i]] + values[i]);
                }
            }
        }
        return dp_1[SIZE];
    }

    /**
     * 3.背包可放入的最大价值，可重复放入
     *  Given n kind of items with size Ai and value Vi( each item has an infinite number available)
        and a backpack with size m.
        What's the maximum value can you put into the backpack?
     * 二维数组dp[i][j](0<=i<=weights.length,0<=j<=SIZE)表示在最大容量为j，可放入容量是weights[0..i-1]的情况下可放入的最大价值，可以分为两种情况
     *  1. 当前背包最大容量小于物品的重量，可容纳的最大重量是dp[i-1][j]
     *  2. 当前背包最大容量小于物品重量，可容纳的最大重量是max(dp[i-1][j],dp[i][j-weights[i-1]+values[i-1])  与不可放入的区别是dp[i][j-weights[i-1]+values[i-1]
     * @param weights
     * @param SIZE
     * @return
     */
    public static int maxValueWithSame(int[] weights, int[] values, int SIZE) {
        int num = weights.length;
//        int[][] dp = new int[num+1][SIZE+1];
//
//        for(int i = 1; i <= num; i++) {
//
//            for(int j = 1; j <= SIZE; j++) {
//                if(j < weights[i-1]) dp[i][j] = dp[i-1][j];
//                else {
//                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-weights[i-1]] + values[i-1]);
//                }
//            }
//        }
//        return dp[num][SIZE];

        // 只使用一维数组
        int[] dp_1 = new int[SIZE+1];
        for(int i = 0; i < num; i++) {
            for(int j = 1; j <= SIZE; j++) {   // 顺序
                if(j >= weights[i]) {
                    dp_1[j] = Math.max(dp_1[j], dp_1[j-weights[i]] + values[i]);
                }
            }
        }
        return dp_1[SIZE];
    }

    /**
     * 4.满足最大重量，可重复放入求所有可能结果排列数
     *  Given an integer array nums with all positive numbers and no duplicates,
        find the number of possible combinations that add up to a positive integer target.
     * 二维数组dp[i][j](0<=i<=weights.length,0<=j<=SIZE)表示在最大容量为j，可放入容量是weights[0..i-1]的情况下所有放入可能，初始dp[0][0] = 1, 可以分为两种情况
     *  1. 当前背包最大容量小于物品的重量，可容纳的最大重量是 dp[i-1][j]
     *  2. 当前背包最大容量小于物品重量，可容纳的最大重量是   dp[i-1][j] + dp[num][j-weights[i-1] , 这里若认为只是顺序不同但是相同的结果即求组合数，则最大重量为 dp[i-1][j] + dp[i][j-weights[i-1]
     *  该方法外循环是容量SIZE，内循环是不同的容量
     * @param weights
     * @param SIZE
     * @return
     */
    public static int maxCombinationsMatchTargetWithSame(int[] weights, int SIZE) {
        int num = weights.length;
        int[][] dp = new int[num+1][SIZE+1];
        for(int i = 0; i <= num; i++) {
            dp[i][0] = 1;
        }
        for(int j = 1; j <= SIZE; j++) {
            for(int i = 1; i <= num; i++) {

                if(j < weights[i-1]) dp[i][j] = dp[i-1][j];
                else {
                    dp[i][j] = dp[i-1][j] + dp[num][j-weights[i-1]];
                }
            }
        }
        return dp[num][SIZE];

//        // 只使用一维数组
//        int[] dp_1 = new int[SIZE+1];
//        dp_1[0] = 1;
//        for(int j = 1; j <= SIZE; j++) {   // 顺序
//            for(int i = 0; i < num; i++) {
//                if(j >= weights[i]) {
//                    dp_1[j] = dp_1[j] + dp_1[j-weights[i]];
//                }
//            }
//        }
//        return dp_1[SIZE];
    }


    /**
     * 5.满足最大重量，不可重复放入求所有可能结果数
     *  Given n items with size nums[i] which an integer array and all positive numbers.
        An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
        Each item may only be used once
     * 二维数组dp[i][j](0<=i<=weights.length,0<=j<=SIZE)表示在最大容量为j，可放入容量是weights[0..i-1]的情况下所有放入可能，初始dp[0][0] = 1, 可以分为两种情况
     *  1. 当前背包最大容量小于物品的重量，可容纳的最大重量是 dp[i-1][j]
     *  2. 当前背包最大容量小于物品重量，可容纳的最大重量是   dp[i-1][j] + dp[i-1][j-weights[i-1]
     * @param weights
     * @param SIZE
     * @return
     */
    public static int maxCombinationsMatchTarget(int[] weights, int SIZE) {
        int num = weights.length;
        int[][] dp = new int[num+1][SIZE+1];
        for(int i = 0; i <= num; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i <= num; i++) {
            for(int j = 1; j <= SIZE; j++) {
                if(j < weights[i-1]) dp[i][j] = dp[i-1][j];
                else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-weights[i-1]];
                }
            }
        }
        return dp[num][SIZE];

//        // 只使用一维数组
//        int[] dp_1 = new int[SIZE+1];
//        dp_1[0] = 1;
//        for(int i = 0; i < num; i++) {
//            for(int j = SIZE; j > 0; j--) {   // 逆序
//                if(j >= weights[i]) {
//                    dp_1[j] = dp_1[j] + dp_1[j-weights[i]];
//                }
//            }
//        }
//        return dp_1[SIZE];
    }
}
