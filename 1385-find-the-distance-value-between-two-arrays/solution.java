class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

        int count = 0;
        for (int x : arr1) {
            boolean farFromAll = true;
            for (int y : arr2) {
                if (Math.abs(x - y) <= d) {
                    farFromAll = false;
                    break;               // one close element is enough to reject x
                }
            }
            if (farFromAll) count++;
        }
        return count;
    }
}
        
    
