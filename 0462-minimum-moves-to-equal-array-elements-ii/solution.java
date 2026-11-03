import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int moves = 0;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            moves += nums[j] - nums[i];
            i++;
            j--;
        }
        return moves;
    }
}