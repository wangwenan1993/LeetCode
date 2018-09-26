package iie.cloud.util;

import iie.cloud.bean.Beans.*;

/**
 * @Author wangwenan
 * @data 2018/8/25 11:23
 */
public class ListUtil {

    public static SingleNode constructSingleList(int number) {
        if (number <= 0) return null;
        SingleNode root = new SingleNode(0);
        SingleNode curr = root;
        for(int i = 1; i < number; i++) {
            curr.next = new SingleNode(i);
            curr = curr.next;
        }
        return root;
    }

    public static DoubleNode constructDoubleList(int number) {
        if (number <= 0) return null;
        DoubleNode root = new DoubleNode(0);
        DoubleNode curr = root;
        for(int i = 1; i < number; i++) {
            curr.next = new DoubleNode(i);
            curr.next.pre = curr;
            curr = curr.next;
        }
        return root;
    }

    public static void printList(DoubleNode root) {
        DoubleNode curr = root;
        while(curr != null) {
            System.out.print(curr.val);
            curr = curr.next;
        }
        System.out.println();
    }
}
