package com.lzb.fund;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 8, 9, 12, 15, 18, 21};
        System.out.println(binarySearch(nums, 22));
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right-left)/2;
            if (target > nums[mid]) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
