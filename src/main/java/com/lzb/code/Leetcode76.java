package com.lzb.code;


public class Leetcode76 {
    /**
     * 给你一个字符串 S、一个字符串 T 。
     * 请你设计一种算法，可以在 O(n) 的时间复杂度内，
     * 从字符串 S 里面找出：包含 T 所有字符的最小子串。
     * 输入：S = "ADOBECODEBANC", T = "ABC"
     * 输出："BANC"
     */

    public static String minWindow(String s, String t) {
        int[] mp = new int[256];
        for (char c : t.toCharArray()) mp[c] += 1;
        int left = 0, right = 0;
        int n = s.length(), m = t.length();
        int cnt = 0;
        int winSize = -1;
        String res = "";
        while (right < n) {
            char c = s.charAt(right);
            mp[c] -= 1;
            if (mp[c] >= 0) cnt += 1;
            while (cnt == m) {
                // can be optimised here?
                if (winSize == -1 || winSize > right - left + 1) {
                    res = s.substring(left, right + 1);
                    winSize = right - left + 1;
                }
                c = s.charAt(left);
                mp[c] += 1;
                if (mp[c] >= 1) cnt -= 1;
                left += 1;
            }
            right += 1;
        }
        return res;
    }
}
