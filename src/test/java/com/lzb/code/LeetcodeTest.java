package com.lzb.code;

import org.junit.Assert;
import org.junit.Test;

public class LeetcodeTest {
    @Test
    public void LeetCode14Test() {
        long start = System.nanoTime();
        String[] str1 = new String[]{"flower", "flow", "flight", "fuck"};
        Assert.assertEquals("f", Leetcode14.longestCommonPrefix(str1));
        String[] str2 = new String[]{"g1g2groupGroup", "g1g3groupGroup", "g2g2groupGroup"};
        Assert.assertEquals("g", Leetcode14.longestCommonPrefix(str2));
        String[] str3 = new String[]{"", "flow", "flight", "fuck"};
        Assert.assertEquals("", Leetcode14.longestCommonPrefix(str3));
        System.out.println("LeetCode14Test costs time in nano seconds: " + (System.nanoTime()-start));
    }

    @Test
    public void LeetCode22Test() {
        long start = System.nanoTime();
        Assert.assertNotNull(Leetcode22.generateParenthesis(3));
        Assert.assertNotNull(Leetcode22.generateParenthesis(5));
        System.out.println("LeetCode22Test costs time in nano seconds: " + (System.nanoTime()-start));
    }

    @Test
    public void LeetCode76Test() {
        long start = System.nanoTime();
        Assert.assertEquals("BANC", Leetcode76.minWindow("ADOBECODEBANC", "ABC"));
        Assert.assertEquals("", Leetcode76.minWindow("hsadljcuas", "adidas"));
        Assert.assertEquals("34odmap", Leetcode76.minWindow("uwe34odmappc", "3pa"));
        System.out.println("LeetCode76Test costs time in nano seconds: " + (System.nanoTime()-start));
    }

    @Test
    public void LeetCode188Test() {
        long start = System.nanoTime();
        Assert.assertEquals(4, Leetcode300.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        Assert.assertEquals(0, Leetcode300.lengthOfLIS(new int[]{}));
        Assert.assertEquals(3, Leetcode300.lengthOfLIS(new int[]{2, 9, 73, -5, 0}));
        System.out.println("LeetCode76Test costs time in nano seconds: " + (System.nanoTime()-start));
    }
    
    @Test
    public void LeetCode300Test() {
        long start = System.nanoTime();
        Assert.assertEquals(2, Leetcode188.maxProfit_k_any(2, new int[]{2, 4, 1}));
        Assert.assertEquals(7, Leetcode188.maxProfit_k_any(2, new int[]{3, 2, 6, 5, 0, 3}));
        Assert.assertEquals(0, Leetcode188.maxProfit_k_any(3, new int[]{3}));
        System.out.println("LeetCode188Test costs time in nano seconds: " + (System.nanoTime()-start));
    }

}