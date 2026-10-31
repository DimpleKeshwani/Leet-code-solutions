public class Solution {
    private int[][][] memo;
    private int m, n;
    private final int MOD = 1000000007;

    public int uniquePaths(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        
        // memo[i][j][0] for moving Right, memo[i][j][1] for moving Down
        // -1 means the sub-problem has not been solved yet
        this.memo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = -1;
                memo[i][j][1] = -1;
            }
        }

        // From the starting point (0,0), the robot can initiate a move 
        // either to the Right (cell 0,1 with dir 0) or Down (cell 1,0 with dir 1)
        long totalPaths = (long) dfs(0, 1, 0, grid) + dfs(1, 0, 1, grid);
        return (int) (totalPaths % MOD);
    }

    private int dfs(int i, int j, int dir, int[][] grid) {
        // 1. Out of Bounds Check (Invalid path)
        if (i >= m || j >= n) {
            return 0;
        }

        // 2. Goal Reached Check
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        // 3. Look up in our Notebook (Memoization check)
        if (memo[i][j][dir] != -1) {
            return memo[i][j][dir];
        }

        int result = 0;

        // 4. Mirror Cell Logic (Forced Redirection)
        if (grid[i][j] == 1) {
            if (dir == 0) { // Entered moving Right -> Deflected Down
                result = dfs(i + 1, j, 1, grid);
            } else {        // Entered moving Down -> Deflected Right
                result = dfs(i, j + 1, 0, grid);
            }
        } 
        // 5. Empty Cell Logic (Choice to continue or turn)
        else {
            // Choice A: Move Right (dir = 0)
            int choiceRight = dfs(i, j + 1, 0, grid);
            // Choice B: Move Down (dir = 1)
            int choiceDown = dfs(i + 1, j, 1, grid);
            
            result = (choiceRight + choiceDown) % MOD;
        }

        // Save the result to our notebook before returning
        memo[i][j][dir] = result;
        return result;
    }
}
