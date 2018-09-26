package iie.cloud.leetcode;

/**
 *  Given an unsorted array of integers, find the length of longest increasing subsequence.
 *  For example,
 *  Given [10, 9, 2, 5, 3, 7, 101, 18],
 *  The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 *  Your algorithm should run in O(n2) complexity.
 *  Follow up: Could you improve it to O(n log n) time complexity? 
 * @author Administrator
 *
 */

//minmax[k] 保存当前长度为k的递增子串的最小的结束值
public class _300_LongestIncreasingSubset {
	public int lengthOfLIS(int[] nums) {
		int[] minmax = new int[nums.length];
		int size = 0;
		for (int x : nums) {
			int i = 0, j = size;
			while (i != j) {
				int m = (i + j) / 2;
				if (minmax[m] < x)
					i = m + 1;
				else
					j = m;
			}
			minmax[i] = x;
			if (i == size) ++size;
		}
		return size;
	}

	public static void main(String[] args) {
		_300_LongestIncreasingSubset lis = new _300_LongestIncreasingSubset();
		int nums[] = {45, 6, 12, 8, 3, 32, 52};
		System.out.println(lis.lengthOfLIS(nums));
		System.out.println();
	}
}

/* python 版本*/
//def lengthOfLIS(self, nums):
//    tails = [0] * len(nums)
//    size = 0
//    for x in nums:
//        i, j = 0, size
//        while i != j:
//            m = (i + j) / 2
//            if tails[m] < x:
//                i = m + 1
//            else:
//                j = m
//        tails[i] = x
//        size = max(i + 1, size)
//    return size

