package iie.cloud.tree;
import iie.cloud.bean.Beans.*;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:
 2
 / \
 1   3
 Binary tree [2,1,3], return true.
 Example 2:
 1
 / \
 2   3
 Binary tree [1,2,3], return false.
 * 思路：可以先对树进行中序遍历，得到遍历的节点值得集合，然后在集合中找是否有逆序的数据
 * @author Administrator
 *
 */
public class _98_ValidateBinarySearchTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
//		System.out.println(root.var);
		root.left = new TreeNode(3);
//		System.out.println(root.left.var);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(13);
		root.right.left = new TreeNode(4);
		System.out.println(isValidBST(root));
	}


	public static boolean isValidBST(TreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public static boolean isValidBST(TreeNode root, long minVal, long maxVal) {
		if (root == null) return true;
		if (root.val >= maxVal || root.val <= minVal) return false;
		return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
	}


	/**
	 * 有问题，没办法找出非相连节点不合法的情况,如：
	 * TreeNode root = new TreeNode(10);
	 //		System.out.println(root.var);
	 root.left = new TreeNode(3);
	 //		System.out.println(root.left.var);
	 root.left.right = new TreeNode(4);
	 root.right = new TreeNode(13);
	 root.right.left = new TreeNode(4);
	 * @param root
	 * @return
	 */

	public static boolean isValidBSTRecursive(TreeNode root) {
		if(root == null) {
			return true;
		}
		if(root.left != null && root.left.val > root.val) {
			return false;
		}
		if(root.right != null && root.right.val < root.val) {
			return false;
		}
		boolean is_left_valid = isValidBSTRecursive(root.left);
		boolean is_right_valid = isValidBSTRecursive(root.right);
		return is_left_valid && is_right_valid;
	}
}
