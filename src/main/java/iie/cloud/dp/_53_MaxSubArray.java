package iie.cloud.dp;

/**
 * Created by Administrator on 2018/3/4.
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class _53_MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(dp(nums));
    }

    private static int dp(int[] nums) {
        int[] dp = new int[nums.length];
        int[] dp_b_idx = new int[nums.length];
        dp[0] = nums[0];
        dp_b_idx[0] = 0;
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] + dp[i-1] > nums[i]) {
                dp[i] = nums[i] + dp[i-1];
                dp_b_idx[i] = dp_b_idx[i-1];
            } else {
                dp[i] = nums[i];
                dp_b_idx[i] = i;
            }
        }

        int biggest_sum = dp[0];
        int biggest_sum_index = 0;
        for(int i = 1; i < dp.length; i++) {
            if (dp[i] > biggest_sum) {
                biggest_sum = dp[i];
                biggest_sum_index = i;
            }
        }
        for(int i = dp_b_idx[biggest_sum_index]; i <= biggest_sum_index; i++) {
            System.out.print(String.valueOf(nums[i]) + ' ');
        }
        System.out.println();
        return biggest_sum;
    }
}
