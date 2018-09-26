package iie.cloud.leetcode;

import iie.cloud.bean.Beans;
import iie.cloud.bean.Beans.*;

/**
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * @author wangwenan
 *
 */
public class _2_AddTwoLinkNumber {
    public SingleNode addTwoNumbers(SingleNode l1, SingleNode l2) {
        SingleNode result = new SingleNode(0);
        int carryBit = 0;
        result.val = (l1.val + l2.val + carryBit) % 10;
        if(l1.val + l2.val + carryBit >= 10) {
            carryBit = 1;
        }
        else {
            carryBit = 0;
        }
        SingleNode list = result;
        while(l1.next != null || l2.next != null || carryBit == 1) {
            SingleNode temp = new SingleNode(0);
            if(l1.next == null && l2.next == null) { //针对两个链表数字的最高位还有进位的情况[5] + [5]
                temp.val = 1;
                carryBit = 0;
                list.next = temp;
                list = list.next;
                break;
            }
            if(l1.next != null && l2.next != null) {
                l1 = l1.next;
                l2 = l2.next;
                temp.val = (l1.val + l2.val + carryBit) % 10;
                if(l1.val + l2.val + carryBit >= 10) {
                    carryBit = 1;
                }
                else {
                    carryBit = 0;
                }
            }
            else if(l1.next == null) {     //l1链表为空
                l2 = l2.next;
                temp.val = (0 + l2.val + carryBit) % 10;
                if(0 + l2.val + carryBit >= 10) {
                    carryBit = 1;
                }
                else {
                    carryBit = 0;
                }
            }
            else if(l2.next == null) {     //l2链表为空
                l1 = l1.next;
                temp.val = (l1.val + 0 + carryBit) % 10;
                if(l1.val + 0 + carryBit >= 10) {
                    carryBit = 1;
                }
                else {
                    carryBit = 0;
                }
            }
            list.next = temp;
            list = list.next;
        }
        return result;
    }

    /**
     * other people's java version
     * @param l1
     * @param l2
     * @return
     */
/*    public SingleNode addTwoNumbers(SingleNode l1, SingleNode l2) {
        SingleNode current = new SingleNode(0);
        SingleNode head = current;

        int shift = 0;
        do
        {
            current.val = ((l1!=null)?l1.val:0) + ((l2!=null)?l2.val:0) + shift;
            shift = current.val / 10;
            current.val%=10;
            l1 = (l1!=null)?l1.next:null;
            l2 = (l2!=null)?l2.next:null;
            if((l1==null)&&(l2==null)){break;}

            current.next = new SingleNode(0);
            current = current.next;
        }while(l1!=null || l2!=null);
        if(shift>0){current.next = new SingleNode(1);}
        return head;
    }*/

    /**
     * C++版本
     */
    /**
     * Definition for singly-linked list.
     * struct SingleNode {
     *     int val;
     *     SingleNode *next;
     *     SingleNode(int x) : val(x), next(NULL) {}
     * };
     */
    /*SingleNode* addTwoNumbers(SingleNode* l1, SingleNode* l2) {
        int carry = 0;
        SingleNode* tail  = new SingleNode(-1);
        SingleNode* head  = tail;

        while(l1 || l2){
            int res = (l1?l1->val:0) + (l2?l2->val:0) + carry;
            tail->next = new SingleNode(res>9? res-10:res);  tail = tail->next;  carry = res>9? 1: 0;
            if(l1)l1=l1->next; if(l2)l2=l2->next;
        }
        if(carry) tail->next = new SingleNode(1);

        return head->next;
    }*/

    /**
     * python版本
     */
    /*# Definition for singly-linked list.
    # class SingleNode(object):
    #     def __init__(self, x):
    #         self.val = x
    #         self.next = None

    class Solution(object):
        def addTwoNumbers(self, l1, l2):
            """
            :type l1: SingleNode
            :type l2: SingleNode
            :rtype: SingleNode
            """
            ret = SingleNode(0)
            cur = ret
            add = 0
            
            while l1 or l2 or add:
                val = (l1.val if l1 else 0) + (l2.val if l2 else 0) + add
                add = val / 10
                cur.next = SingleNode(val % 10)
                cur = cur.next
                l1 = l1.next if l1 else None
                l2 = l2.next if l2 else None
            
            return ret.next*/
}