package com.lzb.code;

public class Leetcode14 {
    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     * 例子：
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = twoStrCommonPrefix(prefix, strs[i]);
            if (prefix.equals("")) {
                return "";
            }
        }
        return prefix;
    }

    private static String twoStrCommonPrefix(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "";
        }
        int len = Math.min(str1.length(), str2.length());
        for (int i = len; i >= 0; i--) {
            String prefix = str1.substring(0, i);
            if (prefix.equals(str2.substring(0, i))) {
                return prefix;
            }
        }
        return "";
    }
}
