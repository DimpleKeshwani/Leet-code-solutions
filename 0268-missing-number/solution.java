class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int xorSum = n; // Initialize with 'n' since the loop only goes up to n-1
        
        for (int i = 0; i < n; i++) {
            // XOR the index 'i' and the actual value 'nums[i]'
            xorSum ^= i ^ nums[i];
        }
        
        return xorSum;
    }
}
