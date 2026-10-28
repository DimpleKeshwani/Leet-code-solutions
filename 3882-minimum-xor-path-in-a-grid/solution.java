import java.util.*;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j][v] will be true if an XOR total of 'v' is possible at cell (i, j)
        // Since grid values <= 1023, the maximum possible XOR value is 1023.
        boolean[][][] dp = new boolean[m][n][1024];
        
        // Base case: Starting at the top-left cell
        dp[0][0][grid[0][0]] = true;
        
        // Traverse the grid row by row, column by column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // Try moving from the cell above (Down step)
                if (i > 0) {
                    for (int v = 0; v < 1024; v++) {
                        if (dp[i - 1][j][v]) {
                            dp[i][j][v ^ grid[i][j]] = true;
                        }
                    }
                }
                
                // Try moving from the cell to the left (Right step)
                if (j > 0) {
                    for (int v = 0; v < 1024; v++) {
                        if (dp[i][j - 1][v]) {
                            dp[i][j][v ^ grid[i][j]] = true;
                        }
                    }
                }
                
            }
        }
        
        // Find the smallest XOR value that is marked true in the bottom-right cell
        for (int v = 0; v < 1024; v++) {
            if (dp[m - 1][n - 1][v]) {
                return v;
            }
        }
        
        return 0;
    }
}
