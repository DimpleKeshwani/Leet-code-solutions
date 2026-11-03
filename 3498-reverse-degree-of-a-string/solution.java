class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int rev = 'z' - s.charAt(i) + 1;  // 'a' -> 26, 'z' -> 1
            total += rev * (i + 1);
        }
        return total;
    }
}