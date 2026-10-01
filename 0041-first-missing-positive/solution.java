class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 1. Place each number in its correct position if possible
        for (int i = 0; i < n; i++) {
            // While the current number is in the valid range [1, n]
            // and is not already at its correct index (nums[i] - 1), swap it.
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int correctIdx = nums[i] - 1;
                
                // Swap nums[i] and nums[correctIdx]
                int temp = nums[i];
                nums[i] = nums[correctIdx];
                nums[correctIdx] = temp;
            }
        }

        // 2. Find the first index where the number is incorrect
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1; // This is the smallest missing positive integer
            }
        }

        // 3. If all numbers from 1 to n are in their correct spots, return n + 1
        return n + 1;
    }
}
