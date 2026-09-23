import java.util.Arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        // 1. Create a combined array to hold all elements
        int[] merged = new int[m + n];
        
        // 2. Copy elements from both arrays into the new array
        System.arraycopy(nums1, 0, merged, 0, m);
        System.arraycopy(nums2, 0, merged, m, n);
        
        // 3. Sort the combined array
        Arrays.sort(merged);
        
        // 4. Calculate and return the median
        int totalLength = merged.length;
        if (totalLength % 2 != 0) {
            // Odd number of elements: return the middle element
            return merged[totalLength / 2];
        } else {
            // Even number of elements: return the average of the two middle elements
            return (merged[(totalLength / 2) - 1] + merged[totalLength / 2]) / 2.0;
        }
    }
}
