class Solution {
    public int maxNumberOfBalloons(String text) {
        int b=0,a=0,l=0,o=0,n=0;
        for(int i=0;i<text.length();i++){
            switch(text.charAt(i)){
                case 'b': b++; break;
                case 'a': a++; break;
                case 'l': l++; break;
                case 'o': o++; break;
                case 'n': n++; break;
            }
        }
        l= l/2;
        o= o/2;
        int result =b;
        result= Math.min(result,a);
        result = Math.min(result,l);
        result = Math.min(result,o);
        result = Math.min(result,n);
        return result;
        
    }
}