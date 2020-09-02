package com.lzb.fund;

import java.util.Arrays;

public class QuickSorting {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 9, -2, 5, 23, 16, 4};
        quickSort(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(nums, left, right);
        quickSort(nums, left, mid-1);
        quickSort(nums, mid+1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        // can be optimised
        int pilot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pilot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pilot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pilot;
        return left;
    }

}
