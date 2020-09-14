package com.lzb.fund.algorithm;

import com.lzb.struct.TreeNode;

import java.util.*;

public class TreeNodePostOrder {
    // left -> right -> root
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);

        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        t3.left = t5;

        System.out.println("=====traverseRe works : ======");
        traverseRe(t1);
        System.out.println("=====traverseIt works : ======");
        traverseIt(t1);
    }

    public static void traverseRe(TreeNode root) {
        if (root == null) {
            return;
        }
        traverseRe(root.left);
        traverseRe(root.right);
        System.out.println(root.val);
    }

    public static void traverseIt(TreeNode root) {
        // left -> right -> root reverse root -> right -> left
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        stack.push(cur);
        while (cur != null && !stack.isEmpty()) {
            cur = stack.pop();
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
            list.add(0, cur.val);
        }
        System.out.println(list);
    }
}
