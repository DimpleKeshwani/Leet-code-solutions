import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // Base case: If the current string reaches the maximum length required
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // Rule 1: You can always add an open parenthesis if you haven't reached the limit 'n'
        if (open < max) {
            current.append("(");
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }

        // Rule 2: You can only add a close parenthesis if it doesn't exceed the number of open ones
        if (close < open) {
            current.append(")");
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        System.out.println("Combinations for n = " + n + ": " + sol.generateParenthesis(n));
    }
}
