import java.util.*;

class Solution {
    int m;
    long[] cntBit, sumBit;

    void update(int idx, int dc, long ds) {
        for (; idx <= m; idx += idx & -idx) {
            cntBit[idx] += dc;
            sumBit[idx] += ds;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // coordinate compression
        int[] vals = Arrays.stream(nums).sorted().distinct().toArray();
        m = vals.length;
        cntBit = new long[m + 1];
        sumBit = new long[m + 1];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(vals, nums[i]) + 1;
        }

        int LOG = Integer.highestOneBit(m);
        int c = (x + 1) / 2;               // median is the c-th smallest
        long[] cost = new long[n - x + 1];
        long total = 0;

        for (int i = 0; i < n; i++) {
            update(rank[i], 1, nums[i]);
            total += nums[i];
            if (i >= x) {
                update(rank[i - x], -1, -nums[i - x]);
                total -= nums[i - x];
            }
            if (i >= x - 1) {
                // BIT descent to find the c-th smallest
                int pos = 0;
                long rem = c, sumBelow = 0;
                for (int pw = LOG; pw > 0; pw >>= 1) {
                    int nxt = pos + pw;
                    if (nxt <= m && cntBit[nxt] < rem) {
                        pos = nxt;
                        rem -= cntBit[nxt];
                        sumBelow += sumBit[nxt];
                    }
                }
                long med = vals[pos];                 // value at index pos+1
                long S = sumBelow + rem * med;        // sum of the c smallest
                cost[i - x + 1] = (med * c - S) + ((total - S) - med * (x - c));
            }
        }

        // DP over number of chosen windows
        final long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];
        for (int j = 1; j <= k; j++) Arrays.fill(dp[j], INF);

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];
                if (i >= x && dp[j - 1][i - x] < INF) {
                    dp[j][i] = Math.min(dp[j][i], dp[j - 1][i - x] + cost[i - x]);
                }
            }
        }
        return dp[k][n];
    }
}