package com.lzb.code;

import com.lzb.struct.TreeNode;

/**
 * leetcode 124
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 */
public class Leetcode124 {
    private static int max = Integer.MIN_VALUE;

    // for test
    public static void main(String[] args) {
        // TODO: make the test case singleton
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);

        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        t3.left = t5;
        t3.right = t6;

        System.out.println(maxPathSum(t1));
        System.out.println(maxPathSum(t5));
    }

    // for algorithm
    private static int maxPathSum(TreeNode root) {
        getMax(root);
        return max;
    }

    // 对于一个节点来说有两种情况：
    // 1. 自身作为根结点，加上自己左右子树的最大路径和，得到整个题的最大结果
    // 2. 自身作为过程路径中的节点，加上自己左子树或右子树的最大路径和，向自己的父节点回溯
    private static int getMax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, getMax(root.left));
        int right = Math.max(0, getMax(root.right));
        // 全局变量max存第1种情况
        max = Math.max(max, root.val+left+right);
        // 回溯第二种情况
        return Math.max(left, right) + root.val;
    }
}
