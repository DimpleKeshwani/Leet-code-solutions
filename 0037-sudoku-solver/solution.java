public class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Find an empty cell
                if (board[i][j] == '.') {
                    // Try placing digits from '1' to '9'
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // Tentatively place the character

                            // Recursively try to solve the rest of the board
                            if (solve(board)) {
                                return true; // Found a valid configuration
                            } else {
                                board[i][j] = '.'; // Backtrack
                            }
                        }
                    }
                    return false; // If no number from 1-9 fits, this path is invalid
                }
            }
        }
        return true; // The board is fully and correctly filled
    }

    // Helper method to validate if character 'c' can be placed at board[row][col]
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // Check row constraint
            if (board[row][i] == c) return false;
            
            // Check column constraint
            if (board[i][col] == c) return false;
            
            // Check 3x3 sub-box constraint
            int boxRowIndex = 3 * (row / 3) + i / 3;
            int boxColIndex = 3 * (col / 3) + i % 3;
            if (board[boxRowIndex][boxColIndex] == c) return false;
        }
        return true;
    }
}
