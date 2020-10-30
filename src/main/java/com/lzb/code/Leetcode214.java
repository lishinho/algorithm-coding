package com.lzb.code;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * 示例 1：
 *
 * 输入：s = "aacecaaa"
 * 输出："aaacecaaa"
 */
public class Leetcode214 {
    // greedy algorithm
    // we can try the best choice first and try more
    // in this code, the best choice returns no addition and add the last char to front
    public static String shortestPalindrome(String s) {
        if (s == null || s.length() == 0 || isPalindrome(s)) {
            return s;
        }
        // can be optimised
        for (int i = s.length()-1; i > 0; i--) {
            String res = new StringBuilder(s.substring(i)).reverse().append(s).toString();
            if (isPalindrome(res)) {
                return res;
            }
        }
        // cannot be here
        return s;
    }

    private static boolean isPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

}
