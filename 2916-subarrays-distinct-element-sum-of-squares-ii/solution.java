import java.util.*;

public class Solution {
    private static final int MOD = 1_000_000_007;

    // Segment tree arrays
    private long[] sum;
    private long[] sumSq;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        // Tracks the most recent index where each number appeared
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSumOfSquares = 0;

        for (int j = 0; j < n; j++) {
            int prev = lastSeen.getOrDefault(nums[j], -1);
            
            // Increment the range [prev + 1, j] by 1
            update(1, 0, n - 1, prev + 1, j, 1);
            
            // The root (index 1) now holds the sum of squares for all subarrays ending at j
            totalSumOfSquares = (totalSumOfSquares + sumSq[1]) % MOD;
            
            // Update the last seen position of the element
            lastSeen.put(nums[j], j);
        }

        return (int) totalSumOfSquares;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0) return;

        long val = lazy[node];
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        // Apply lazy value to left child
        apply(leftChild, start, mid, val);
        // Apply lazy value to right child
        apply(rightChild, mid + 1, end, val);

        // Reset current node's lazy value
        lazy[node] = 0;
    }

    private void apply(int node, int start, int end, long val) {
        long len = end - start + 1;
        
        // New sum_sq = sum_sq + 2 * val * sum + val^2 * len
        long term1 = (2 * val % MOD) * sum[node] % MOD;
        long term2 = ((val * val % MOD) * len) % MOD;
        
        sumSq[node] = (sumSq[node] + term1 + term2) % MOD;
        sum[node] = (sum[node] + (val * len) % MOD) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (l <= start && end <= r) {
            apply(node, start, end, val);
            return;
        }

        pushDown(node, start, end);
        int mid = start + (end - start) / 2;

        if (l <= mid) {
            update(2 * node, start, mid, l, r, val);
        }
        if (r > mid) {
            update(2 * node + 1, mid + 1, end, l, r, val);
        }

        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
    }
}
