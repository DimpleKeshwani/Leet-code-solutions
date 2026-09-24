import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordsCount = words.length;
        int totalLen = wordLen * wordsCount;
        int sLen = s.length();

        
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> currentMap = new HashMap<>();
            int count = 0;

            
            while (right + wordLen <= sLen) {
                
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordCountMap.containsKey(word)) {
                    currentMap.put(word, currentMap.getOrDefault(word, 0) + 1);
                    count++;

                   
                    while (currentMap.get(word) > wordCountMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentMap.put(leftWord, currentMap.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    
                    if (count == wordsCount) {
                        result.add(left);
                    }
                } else {
                    
                    currentMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}
