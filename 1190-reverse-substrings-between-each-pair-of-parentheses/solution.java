import java.util.Stack;

public class Solution {
    public static String reverseParentheses(String s) {
        Stack<Integer> openBrackets = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                openBrackets.push(sb.length());
            } else if (ch == ')') {
                int start = openBrackets.pop();
                reverse(sb, start, sb.length() - 1);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
    private static void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseParentheses("(abcd)"));     
        System.out.println(reverseParentheses("(u(love)i)")); 
        System.out.println(reverseParentheses("(ed(et(oc))el)"));
    }
}
