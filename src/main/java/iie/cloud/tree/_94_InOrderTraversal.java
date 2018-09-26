package iie.cloud.tree;

import iie.cloud.util.PrintUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import iie.cloud.bean.Beans.*;


public class _94_InOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
//		System.out.println(root.var);
        root.left = new TreeNode(11);
//		System.out.println(root.left.var);
        root.left.right = new TreeNode(112);
        root.right = new TreeNode(12);
        root.right.right = new TreeNode(122);
        List<Integer> result = inorderTraversalRecursive(root);
        PrintUtil.printIntList(result);
    }

    /**
     * Iterative solution using stack — O(n) time and O(n) space;
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null || !stack.empty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

    /**
     * Recursive solution — O(n) time and O(n) space (considering the spaces of function call stack);
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        recursive(root, result);
        return result;
    }

    private static void recursive(TreeNode cur, List<Integer> result) {
        if(cur == null) return;
        recursive(cur.left, result);
        result.add(cur.val);
        recursive(cur.right, result);
    }

    /**
     * Morris traversal — O(n) time and O(1) space!!!
     * 实现O(1)的原因是每次会将当前节点的左子树的最后遍历元素的右孩子指向当前节点
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre = null;
        while(root != null){
            if(root.left == null){
                res.add(root.val);
                root = root.right;
            }else{
                pre = root.left;
                while(pre.right != null && pre.right != root){
                    pre = pre.right;
                }
                if(pre.right == null){
                    pre.right = root;
                    root = root.left;
                }else{
                    pre.right = null;
                    res.add(root.val);
                    root = root.right;
                }
            }
        }
        return res;
    }
}
