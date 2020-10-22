package com.lzb.code;

import com.lzb.struct.TreeNode;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 */
public class Leetcode99 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.left = t3;
        t3.right = t2;
        // TODO: need test case
    }

    /**
     * 中序遍历过程模板
     * public void inorder(TreeNode root) {
     *         if (root == null) return ;    //终止条件
     *         inorder(root.left);           //访问左子树
     *         对当前节点进行一些操作            //访问根节点-----在遍历过程中希望实现的操作
     *         inorder(root.right);          //访问右子树
     *     }
     */
    private static TreeNode t1, t2, pre;
    // t1, t2 are exchanged nodes
    // pre is currnet node
    private static void recoverTree(TreeNode root) {
        inorder(root);
        int tmp = t1.val;
        t1.val = t2.val;
        t2.val = tmp;
    }

    private static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (pre != null && pre.val > root.val) {
            t1 = pre;
            t2 = root;
        }
        pre = root;
        inorder(root.right);
    }
}
