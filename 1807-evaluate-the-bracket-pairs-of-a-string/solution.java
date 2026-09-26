import java.util.*;

public class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Convert the knowledge list into a fast-lookup HashMap
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        int i = 0;
        int n = s.length();
        
        // Step 2: Parse the string
        while (i < n) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                // Find the closing bracket
                int start = i + 1;
                while (i < n && s.charAt(i) != ')') {
                    i++;
                }
                // Extract the key inside the brackets
                String key = s.substring(start, i);
                
                // Replace with the map value, or "?" if missing
                result.append(map.getOrDefault(key, "?"));
            } else {
                // Just a regular character, append it
                result.append(ch);
            }
            i++;
        }
        
        return result.toString();
    }
}
