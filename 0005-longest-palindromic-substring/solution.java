class Solution {
    public String longestPalindrome(String s) {
        int start = 0, maxLen = 1;

        for (int center = 0; center < s.length(); center++) {
            int odd = expand(s, center, center);        // "aba"
            int even = expand(s, center, center + 1);   // "abba"
            int len = Math.max(odd, even);

            if (len > maxLen) {
                maxLen = len;
                start = center - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    // returns the length of the palindrome grown outward from (left, right)
    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}