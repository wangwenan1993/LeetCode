package iie.cloud.tree;

import iie.cloud.bean.Beans.*;

/**
 * Created by Administrator on 2018/8/26.
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]

 _______6______
 /              \
 ___2__          ___8__
 /      \        /      \
 0      _4       7       9
 /  \
 3   5
 Example 1:

 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 Output: 6
 Explanation: The LCA of nodes 2 and 8 is 6.
 Example 2:

 Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 Output: 2
 Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself
 according to the LCA definition.
 */
public class _235_LowestCommonAncestorOfBST {

    /**
     * 由于是二叉搜索树，故可以通过值判断节点p和q相对于root节点的位置
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = null, right = null;
        if(p.val < root.val && root.val < q.val || p.val > root.val && root.val > q.val) {  // p和q不在同一子树，直接返回当前root节点
            return root;
        }
        if (p.val < root.val && q.val < root.val) {   // p和q都在左子树
            left = lowestCommonAncestor(root.left, p, q);
        } else {                                             // p和q都在右子树
            right = lowestCommonAncestor(root.right, p, q);
        }
        return left != null ? left : right;
    }

}
