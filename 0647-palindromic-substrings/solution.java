class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;

        for (int center = 0; center < n; center++) {
            count += expand(s, center, center);      // odd length: "aba"
            count += expand(s, center, center + 1);  // even length: "abba"
        }
        return count;
    }

    // counts palindromes by growing outward while both ends match
    private int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
}
        
    
