package com.leetcode.problem.movingzeros;

public class MovingZeros {
    public int[] moveZeroes(int[] nums) {

        if (nums == null || nums.length == 0) {
            return nums;
        }
        for (int i = 0, g = 1; i < nums.length && g < nums.length; ) {
            if (nums[i] == 0 && nums[g] != 0) {
                nums[i] = nums[g];
                nums[g] = 0;
                i++;
                g++;
            }
            if (g < nums.length && nums[i] == 0 && nums[g] == 0) {
                g++;
            }

            if (g < nums.length && nums[i] != 0 && (nums[g] == 0 || nums[g] != 0)) {
                i++;
                g++;
            }
        }
        return nums;
    }
}
