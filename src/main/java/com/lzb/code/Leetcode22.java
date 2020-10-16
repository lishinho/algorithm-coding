package com.lzb.code;

import java.util.ArrayList;
import java.util.List;

public class Leetcode22 {
    /**
     *数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 输入：n = 3
     * 输出：[
     *        "((()))",
     *        "(()())",
     *        "(())()",
     *        "()(())",
     *        "()()()"
     *      ]
     */
    public static List<String> generateParenthesis(int n) {
        List<String> strs = new ArrayList<>();
        generate(strs, "", 0, 0, n);
        return strs;
    }

    private static void generate(List<String> strs, String str, int leftCnt, int rightCnt, int n) {
        if (leftCnt > n || rightCnt > n) {
            return;
        }
        if (leftCnt == n && rightCnt == n) {
            strs.add(str);
        }
        // can be optimised here ?
        if (leftCnt >= rightCnt) {
            generate(strs, str+"(", leftCnt+1, rightCnt, n);
            generate(strs, str+")", leftCnt, rightCnt+1, n);

        }
    }
}
