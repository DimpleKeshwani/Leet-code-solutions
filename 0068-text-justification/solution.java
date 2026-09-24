import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = words.length;

        while (i < n) {
            int j = i + 1;
            int lineLength = words[i].length();

           
            while (j < n && lineLength + 1 + words[j].length() <= maxWidth) {
                lineLength += 1 + words[j].length();
                j++;
            }

            StringBuilder sb = new StringBuilder();
            int numWords = j - i;

       
            if (j == n || numWords == 1) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        sb.append(" ");
                    }
                }
         
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } 
           
            else {
           
                int totalWordsLength = 0;
                for (int k = i; k < j; k++) {
                    totalWordsLength += words[k].length();
                }
                int totalSpaces = maxWidth - totalWordsLength;
                
                int gaps = numWords - 1;
                int baseSpaces = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                      
                        int spacesToApply = baseSpaces + (k - i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToApply; s++) {
                            sb.append(" ");
                        }
                    }
                }
            }

            result.add(sb.toString());
            i = j; // Move pointer to the next set of words
        }

        return result;
    }
}
