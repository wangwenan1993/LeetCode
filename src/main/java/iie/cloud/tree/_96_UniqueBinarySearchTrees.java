package iie.cloud.tree;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 * @author Administrator
 *
 */
public class _96_UniqueBinarySearchTrees {
	
	public static void main(String[] args) {
		System.out.println(numTreesDP(1));
	}
	
	public static int numTreesDP(int n) {
		int[] result = new int[n+1];
		result[0] = 1;
		result[1] = 1;
		if(n <= 1) return result[n];
		for(int i = 2; i <= n; i++) {
			for(int j = 1; j <= i; j++) {
				result[i] += result[j-1] * result[i-j];
			}
		}
		return result[n];
	}
}
