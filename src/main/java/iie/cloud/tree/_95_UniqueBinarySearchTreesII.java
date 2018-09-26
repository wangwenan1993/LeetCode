package iie.cloud.tree;

import iie.cloud.bean.Beans.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

 For example,
 Given n = 3, your program should return all 5 unique BST's shown below.

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3
 */
public class _95_UniqueBinarySearchTreesII {

    public static void main(String[] args) {
        List<TreeNode> res = generateTreesRecursive(3);
        for(TreeNode root: res) {
            List<Integer> l = PreOrderTraversal.preorderTraversalRecursive(root);
            for (int i = 0; i < l.size(); i++) {
                System.out.print(l.get(i) + " ");
            }
            System.out.println();
        }
    }

    /**
     * I start by noting that 1…n is the in-order traversal for any BST with nodes 1 to n. So if I pick i-th node as my root, the left subtree will contain elements 1 to (i-1), and the right subtree will contain elements (i+1) to n.
     * I use recursive calls to get back all possible trees for left and right subtrees and combine them in all possible ways with the root.
     * @param n
     * @return
     */
    private static List<TreeNode> generateTreesRecursive(int n) {
        List<TreeNode> list = genSubTree(1, n);
        return list;
    }

    private static List<TreeNode> genSubTree(int start, int end) {
        List<TreeNode> res = new ArrayList<TreeNode>();

        if(start > end) {
            res.add(null);
            return res;
        }
        if(start == end) {
            res.add(new TreeNode(start));
            return res;
        }

        for(int i = start; i <= end; i++) {
            List<TreeNode> s_left = genSubTree(start, i-1);
            List<TreeNode> s_right = genSubTree(i+1, end);

            for(TreeNode left: s_left) {
                for(TreeNode right: s_right) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }

    /**
     * result[i] stores the result until length i. For the result for length i+1, select the root node j from 0 to i, combine the result from left side and right side.
     * Note for the right side we have to clone the nodes as the value will be offsetted by j.
     * @param n
     * @return
     */
    public static List<TreeNode> generateTreesDP(int n) {
        List<TreeNode>[] result = new List[n + 1];
        result[0] = new ArrayList<TreeNode>();
        if (n == 0) {
            return result[0];
        }

        result[0].add(null);
        for (int len = 1; len <= n; len++) {
            result[len] = new ArrayList<TreeNode>();
            for (int j = 0; j < len; j++) {
                for (TreeNode nodeL : result[j]) {
                    for (TreeNode nodeR : result[len - j - 1]) {
                        TreeNode node = new TreeNode(j + 1);
                        node.left = nodeL;
                        node.right = clone(nodeR, j + 1);
                        result[len].add(node);
                    }
                }
            }
        }
        return result[n];
    }

    private static TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }
}
