class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int numIslands = 0;
        int m = grid.length;
        int n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }
        
        return numIslands;
    }
    
    private void dfs(char[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Base case: check for boundaries and water
        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == '0') {
            return;
        }
        
        // Mark the current cell as visited by converting it to water
        grid[r][c] = '0';
        
        // Explore all 4 adjacent directions
        dfs(grid, r - 1, c); // Up
        dfs(grid, r + 1, c); // Down
        dfs(grid, r, c - 1); // Left
        dfs(grid, r, c + 1); // Right
    }
}
