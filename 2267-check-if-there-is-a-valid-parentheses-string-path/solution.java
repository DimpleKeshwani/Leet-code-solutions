class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // A valid path length is always m + n - 1. 
        // If the path length is odd, it's impossible to pair the parentheses.
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        // If the start isn't '(' or the end isn't ')', it can never be valid.
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // visited[r][c][balance] to memoize states we've already checked
        // Max possible balance is bounded by the max path length (m + n)
        boolean[][][] visited = new boolean[m][n][m + n];
        
        return dfs(grid, 0, 0, 0, visited);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, boolean[][][] visited) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Update balance based on the current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }
        
        // If balance goes negative, closed brackets exceeded open brackets -> Invalid path
        if (balance < 0) {
            return false;
        }
        
        // If we reached the bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        
        // If we have already evaluated this state, return false 
        // (If it were true, the execution wouldn't reach here again as it would have returned true early)
        if (visited[r][c][balance]) {
            return false;
        }
        
        // Mark the state as visited
        visited[r][c][balance] = true;
        
        // Move Right
        if (c + 1 < n && dfs(grid, r, c + 1, balance, visited)) {
            return true;
        }
        
        // Move Down
        if (r + 1 < m && dfs(grid, r + 1, c, balance, visited)) {
            return true;
        }
        
        return false;
    }
}
