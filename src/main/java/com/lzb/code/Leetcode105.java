package com.lzb.code;

import com.lzb.struct.TreeNode;

/**
 * leetcode 105
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 */
public class Leetcode105 {
    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 5, 7};
        int[] inOrder = new int[]{9, 3, 5, 20, 7};
        buildTree(preOrder, inOrder);
    }

    private static void buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        buildChildTree(0, len-1, 0, len-1, preorder, inorder);
    }

    private static TreeNode buildChildTree(int leftP, int rightP, int leftI, int rightI,
                                           int[] preOrder, int[] inOrder) {
        if (leftP > rightP || leftI > rightI) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[leftP]);
        int rootIn = leftI;
        while (rootIn <= rightI && inOrder[rootIn] != preOrder[leftP]) {
            rootIn++;
        }
        int left = rootIn - leftI;
        root.left = buildChildTree(leftP+1, leftP+left, leftI, rootIn-1, preOrder, inOrder);
        root.right = buildChildTree(leftP+left+1, rightP, rootIn+1, rightI, preOrder, inOrder);
        System.out.println(root.val);
        return root;
    }
}
