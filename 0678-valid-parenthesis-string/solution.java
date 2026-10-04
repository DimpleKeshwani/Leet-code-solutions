class Solution {
    public boolean checkValidString(String s) {
        // low tracks the minimum possible open '('
        // high tracks the maximum possible open '('
        int low = 0;
        int high = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else if (c == '*') {
                // If '*' is treated as ')', low decreases
                low--; 
                // If '*' is treated as '(', high increases
                high++; 
                // (If '*' is treated as "", low and high stay the same, 
                // which is already covered by the range between low and high)
            }

            // If high is negative, it means we have too many close ')' 
            // and not even wildcards can save it.
            if (high < 0) {
                return false;
            }

            // low cannot be negative because we can't have "negative" open brackets.
            // If it drops below 0, it just means we chose to treat some '*' as ')'
            // when we shouldn't have, so we reset it to 0.
            if (low < 0) {
                low = 0;
            }
        }

        // If low is 0, it means we can form a perfectly balanced string.
        return low == 0;
    }
}
