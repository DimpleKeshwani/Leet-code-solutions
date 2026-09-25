import java.util.*;

public class Solution {
    public List<String> braceExpansionII(String expression) {
       
        Stack<Object> stack = new Stack<>();
        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);
            
            if (ch == '{') {
                stack.push(ch);
                i++;
            } else if (ch == '}') {
                List<Set<String>> currentGroup = new ArrayList<>();
                Set<String> currentSet = new TreeSet<>(); 
                currentSet.add("");
                
                while (!stack.isEmpty() && !stack.peek().equals('{')) {
                    Object top = stack.pop();
                    if (top.equals(',')) {
                        currentGroup.add(currentSet);
                        currentSet = new TreeSet<>();
                        currentSet.add("");
                    } else {
                        Set<String> prevSet = (Set<String>) top;
                        Set<String> nextSet = new TreeSet<>();
                        for (String p : prevSet) {
                            for (String c : currentSet) {
                                nextSet.add(p + c);
                            }
                        }
                        currentSet = nextSet;
                    }
                }
                currentGroup.add(currentSet);
                if (!stack.isEmpty() && stack.peek().equals('{')) {
                    stack.pop(); 
                }
                Set<String> unionSet = new TreeSet<>();
                for (Set<String> s : currentGroup) {
                    unionSet.addAll(s);
                }
                autoConcatenate(stack, unionSet);
                i++;
            } else if (ch == ',') {
                stack.push(ch);
                i++;
            } else {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isLowerCase(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                Set<String> literalSet = new TreeSet<>();
                literalSet.add(sb.toString());
                autoConcatenate(stack, literalSet);
            }
        }
        List<Set<String>> finalGroup = new ArrayList<>();
        Set<String> finalSet = new TreeSet<>();
        finalSet.add("");
        
        while (!stack.isEmpty()) {
            Object top = stack.pop();
            if (top.equals(',')) {
                finalGroup.add(finalSet);
                finalSet = new TreeSet<>();
                finalSet.add("");
            } else {
                Set<String> prevSet = (Set<String>) top;
                Set<String> nextSet = new TreeSet<>();
                for (String p : prevSet) {
                    for (String f : finalSet) {
                        nextSet.add(p + f);
                    }
                }
                finalSet = nextSet;
            }
        }
        finalGroup.add(finalSet);
        
        Set<String> resultSet = new TreeSet<>();
        for (Set<String> s : finalGroup) {
            resultSet.addAll(s);
        }
        
        return new ArrayList<>(resultSet);
    }
    
    private void autoConcatenate(Stack<Object> stack, Set<String> currentSet) {
        if (!stack.isEmpty() && stack.peek() instanceof Set) {
            Set<String> prevSet = (Set<String>) stack.pop();
            Set<String> combined = new TreeSet<>();
            for (String p : prevSet) {
                for (String c : currentSet) {
                    combined.add(p + c);
                }
            }
            stack.push(combined);
        } else {
            stack.push(currentSet);
        }
    }
}
