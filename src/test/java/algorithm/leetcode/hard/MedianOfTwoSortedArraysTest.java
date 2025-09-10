package algorithm.leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedianOfTwoSortedArraysTest {
    @Test
    void findMedianSortedArrays() {
        int []num1 =new int[]{1,2};
        int []num2 =new int[]{3,4};
        double medianSortedArrays = MedianOfTwoSortedArrays.findMedianSortedArrays(num1, num2);
        System.out.println(medianSortedArrays);
    }
}