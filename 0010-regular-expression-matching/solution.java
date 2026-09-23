class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char currentPatternChar = p.charAt(j - 1);
                if (currentPatternChar == s.charAt(i - 1) || currentPatternChar == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                else if (currentPatternChar == '*') {
                    dp[i][j] = dp[i][j - 2];
                    char precedingChar = p.charAt(j - 2);
                    if (precedingChar == s.charAt(i - 1) || precedingChar == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}
