package iie.cloud.tree;

import iie.cloud.util.PrintUtil;
import iie.cloud.bean.Beans.*;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 讲解可参考 http://blog.csdn.net/workformywork/article/details/21628351
 */
public class PostOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
//		System.out.println(root.var);
        root.left = new TreeNode(11);
//		System.out.println(root.left.var);
        root.left.right = new TreeNode(112);
        root.right = new TreeNode(12);
        root.right.left = new TreeNode(121);

        TreeNode r = new TreeNode(122);
        root.right.right = r;
        List<Integer> l = new ArrayList<Integer>();
//        printReverse(root, r, l);
//        PrintUtil.printIntList(l);

        List<Integer> result = postorderTraversalMorris(root);
        PrintUtil.printIntList(result);
    }

    /**
     * Iterative solution using stack — O(n) time and O(n) space;
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null || !stack.empty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
//            cur = stack.pop();
            cur = stack.peek();
            if (cur.left == null && cur.right == null) {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode tmp = cur.right;
                cur.right = null;
                cur.left = null;
                cur = tmp;
            }
        }
        return result;
    }

    /**
     * Recursive solution — O(n) time and O(n) space (considering the spaces of function call stack);
     * @param root
     * @return
     */
    private static List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        recursive(root, result);
        return result;
    }

    private static void recursive(TreeNode cur, List<Integer> result) {
        if(cur == null) return;
        recursive(cur.left, result);
        recursive(cur.right, result);
        result.add(cur.val);
    }

    /**
     * Morris traversal — O(n) time and O(1) space!!!
     * 实现O(1)的原因是每次会将当前节点的左子树的最后遍历元素的右孩子指向当前节点
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversalMorris(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        TreeNode r = root, pre = null;
        while(root != null){
            if(root.left == null){                              // 没有左子树
                root = root.right;
            }else{
                pre = root.left;
                while(pre.right != null && pre.right != root){  // 找到左子树最右节点
                    pre = pre.right;
                }
                if(pre.right == null){                          // 让最右节点的右指针指向左子树的根节点（也就是当前节点）
                    pre.right = root;
                    root = root.left;
                }else{                                          // 表示当前节点的左子树已遍历完，开始遍历右子树，同时清空左子树的最右节点的右指针
                    printReverse(root.left, pre, res);
                    pre.right = null;
                    root = root.right;
//                    System.out.println(root.val);
                }
            }
        }

        TreeNode t = r;
        while(t.right != null){  // 逆序添加根节点到最右节点的数据
            t = t.right;
        }
        printReverse(r, t, res);

        return res;
    }

    public static void printReverse(TreeNode from, TreeNode to, List<Integer> res) {
        TreeNode p;
        reverse(from, to);
        p = to;
        while(true) {
            res.add(p.val);
            if(p == from) {
                break;
            }
            p = p.right;
        }
        reverse(to, from);
    }

    /**
     * 相当于单链表的反转
     */
    public static void reverse(TreeNode from , TreeNode to) {
        if(from == to) return;
        TreeNode x = from;
        TreeNode y = from.right;
        TreeNode z;
        while(x != to) {
            z = y.right;
            y.right = x;
            x = y;
            y = z;
        }
    }
}
