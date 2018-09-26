package iie.cloud.bean;

/**
 * @Author wangwenan
 * @data 2018/8/25 11:24
 */
public class Beans {
    public static void main(String[] args) {
        String s = "1001";
        String s1 = "1100";
        System.out.println(Integer.valueOf(s) ^ Integer.valueOf(s1));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Math.pow(2, 1)-1);
    }

    public abstract static class Node {
        public int val = 0;
        public Node next = null;
        public Node(){}
    }

    public static class SingleNode extends Node {
        public int val = 0;
        public SingleNode next = null;
        public SingleNode(int val) {
            this.val = val;
        }
    }

    public static class DoubleNode extends Node {
        public int val = 0;
        public DoubleNode next = null;
        public DoubleNode pre = null;
        public DoubleNode(int val) {
            this.val = val;
        }
    }

    public static class RandomListNode {
        public int label;
        public RandomListNode next = null;
        public RandomListNode random = null;

        public RandomListNode(int label) {
            this.label = label;
        }
    }

    public static class TreeNode extends Node {
        public int val = 0;
        public TreeNode left = null;
        public TreeNode right = null;
        public TreeNode parent = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
