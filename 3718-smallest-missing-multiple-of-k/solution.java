class Solution {
    public int missingMultiple(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int x : nums){
            map.put(x,1);
        }
        int multiple = k;
        while(map.containsKey(multiple)){
            multiple+=k;
        }
        return multiple;

        
    }
}