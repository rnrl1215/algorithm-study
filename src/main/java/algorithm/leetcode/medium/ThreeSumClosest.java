package algorithm.leetcode.medium;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length -1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int retA = Math.abs(target - result);
                int retB = Math.abs(target - sum);

                if (retA >= retB) {
                    result = sum;
                }

                if (target == sum) {
                    return sum;
                }
                if (target > sum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
