package iie.cloud.leetcode;

import java.util.Arrays;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * @author wangwenan
 *
 */
public class _1_TwoSum {
	public int[] twoSum(int[] nums, int target) {
        int temps[] = new int[nums.length];
        for(int i = 0;i <nums.length;i++) {
            temps[i] = nums[i];
        }
        Arrays.sort(temps);
        for(int i = 0;i <temps.length;i++) {
            System.out.println(temps[i]);
        }
        int i = 0, j = nums.length - 1;
        while(i < j) {
        	System.out.println("i + j: " + i + " " + j);
            if(temps[i] + temps[j] > target) {
                j--;
                continue;
            }
            else if(temps[i] + temps[j] < target) {
                i++;
                continue;
            }
            else {
                break;
            }
        }
        System.out.println(i + " " + j);
        int index1 = -1, index2 = -1;
        for(int k=0; k < nums.length; k++) {
            if(nums[k] == temps[i] || nums[k] == temps[j]){
            	if(index1 == -1) {
            		index1 = k;
            	}
            	else {
            		index2 = k;
            	}
            }
        }
        int result[] = new int[]{index1, index2};
        Arrays.sort(result);
        return result;
    }
	public static void main(String args[]) {
		int[] nums = new int[]{3,2,4};
		_1_TwoSum two = new _1_TwoSum();
		int result[] = two.twoSum(nums, 6);
		System.out.println(result[0] + " " + result[1]);
	}
}
