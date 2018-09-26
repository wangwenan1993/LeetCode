package iie.cloud.util;

import iie.cloud.bean.Beans.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author wangwenan
 * @data 2018/8/25 15:23
 */
public class TreeUtil {

    public static void main(String[] args) {
        TreeNode root = constructBinaryTree(7);
        ArrayList<TreeNode> list_pre = preOrderTree(root, 1);
        System.out.println();
        ArrayList<TreeNode> list_in = inOrderTree(root, 1);
        System.out.println();
        ArrayList<TreeNode> list_post = postOrderTree(root, 1);
        System.out.println();
    }

    public static TreeNode constructBinaryTree(int number) {
        if(number <= 0) return null;
        TreeNode root = new TreeNode(0);
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        for(int i = 1; i < number;) {
            TreeNode node = queue.removeFirst();
            if(i == number-1) {
                TreeNode left = new TreeNode(i++);
                queue.add(left);
                node.left = left;
            } else {
                TreeNode left = new TreeNode(i++);
                TreeNode right = new TreeNode(i++);
                queue.add(left);
                queue.add(right);
                node.left = left;
                node.right = right;
            }
        }
        return root;
    }

    /**
     * 先序遍历二叉树
     * @param type 0: 递归, 1: 栈
     */
    public static ArrayList<TreeNode> preOrderTree(TreeNode root, int type) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        if(type == 0) {
            preOrder0(root, list);
        } else {
            preOrder1(root, list);
        }
        return list;
    }
    public static void preOrder0(TreeNode root, ArrayList<TreeNode> list) {
        if(root == null) return;
        list.add(root);
        System.out.print(root.val + " ");
        preOrder0(root.left, list);
        preOrder0(root.right, list);
    }
    public static void preOrder1(TreeNode root, ArrayList<TreeNode> list) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        while(!stack.empty() || curr != null) {
            while(curr != null) {
                stack.add(curr);
                list.add(curr);
                System.out.print(curr.val + " ");
                curr = curr.left;
            }
            curr = stack.pop();
            curr = curr.right;
        }
    }

    /**
     * 中序遍历二叉树
     * @param type 0: 递归, 1: 栈
     */
    public static ArrayList<TreeNode> inOrderTree(TreeNode root, int type) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        if(type == 0) {
            inOrder0(root, list);
        } else {
            inOrder1(root, list);
        }
        return list;
    }
    public static void inOrder0(TreeNode root, ArrayList<TreeNode> list) {
        if(root == null) return;
        inOrder0(root.left, list);
        list.add(root);
        System.out.print(root.val + " ");
        inOrder0(root.right, list);
    }
    public static void inOrder1(TreeNode root, ArrayList<TreeNode> list) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            list.add(curr);
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
    }

    /**
     * 后序遍历二叉树
     * @param type 0: 递归, 1: 栈
     */
    public static ArrayList<TreeNode> postOrderTree(TreeNode root, int type) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        if(type == 0) {
            postOrder0(root, list);
        } else {
            postOrder1(root, list);
        }
        return list;
    }
    public static void postOrder0(TreeNode root, ArrayList<TreeNode> list) {
        if(root == null) return;
        postOrder0(root.left, list);
        postOrder0(root.right, list);
        list.add(root);
        System.out.print(root.val + " ");
    }
    public static void postOrder1(TreeNode root, ArrayList<TreeNode> list) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            curr = stack.peek();
            if (curr.left == null && curr.right == null) {
                curr = stack.pop();
                list.add(curr);
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode tmp = curr.right;
                curr.right = null;
                curr.left = null;
                curr = tmp;
            }
        }
    }
}
