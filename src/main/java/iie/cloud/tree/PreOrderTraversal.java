package iie.cloud.tree;

import iie.cloud.util.PrintUtil;
import iie.cloud.bean.Beans.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
//		System.out.println(root.var);
        root.left = new TreeNode(11);
//		System.out.println(root.left.var);
        root.left.right = new TreeNode(112);
        root.right = new TreeNode(12);
        root.right.right = new TreeNode(122);
        List<Integer> result = preorderTraversalMorris(root);
        PrintUtil.printIntList(result);
    }

    /**
     * Iterative solution using stack — O(n) time and O(n) space;
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null || !stack.empty()) {
            while(cur != null) {
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return result;
    }

    /**
     * Recursive solution — O(n) time and O(n) space (considering the spaces of function call stack);
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        recursive(root, result);
        return result;
    }

    private static void recursive(TreeNode cur, List<Integer> result) {
        if(cur == null) return;
        result.add(cur.val);
        recursive(cur.left, result);
        recursive(cur.right, result);
    }

    /**
     * Morris traversal — O(n) time and O(1) space!!!,
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversalMorris(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre = null;
        while(root != null){
            res.add(root.val);
            if(root.left == null){
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
                    root = root.right;
                    res.remove(res.size() -1);
                }
            }
        }
        return res;
    }
}
