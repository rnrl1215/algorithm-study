package algorithm.leetcode.hard;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MedianOfTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num1Length = nums1.length;
        int num2Length = nums2.length;
        int mergedLength = num1Length + num2Length;

        int [] mergedArray = new int[mergedLength];


        for (int i = 0; i < num1Length; i++) {
            mergedArray[i] = nums1[i];
        }


        for (int i = 0; i < num2Length; i++) {
            mergedArray[i + num1Length] = nums2[i];
        }

        Arrays.sort(mergedArray);

        return solve(mergedArray);
    }

    public static double solve(int[]array) {
        int length = array.length;
        int medianIndex = length / 2;

        double median = 0;
        double oddMdeian = array[medianIndex];
        double evenFirstMedian = array[medianIndex];
        double evenDecondMedian = array[medianIndex-1];
        if (length % 2 == 0) {
            median = (evenFirstMedian + evenDecondMedian)/2;
        } else {
            median = oddMdeian;
        }

        return median;
    }
}
