package iie.cloud.dp;
import java.util.Stack;

/**
 * Created by Administrator on 2018/3/3.
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 For "(()", the longest valid parentheses substring is "()", which has length = 2.

 Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class _32_LongestValidParentheses {
    public static void main(String[] args) {
//        Stack<Character> s = new Stack<>();
//        s.push('a');
//        s.push('b');
//        System.out.println(s.peek());
//        System.out.println(s);
//        System.out.println(s.size());
        String s = "(()(()(";
        System.out.println(stack(s));

        System.out.println(dp(s));
    }

    private static int stack(String s) {
        if (s.length() <= 1) {
            return 0;
        }
        Stack<Character> st = new Stack<Character>();
        Stack<Integer> st_index = new Stack<Integer>();
        int longest = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(s.charAt(i));
                st_index.push(i);
            }
            else {
                if (!st.empty()) {
                    if (st.peek() == '(') {
                        st.pop();
                        st_index.pop();
                    }
                    else {
                        st.push(s.charAt(i));
                        st_index.push(i);
                    }
                } else {
                    st.push(s.charAt(i));
                    st_index.push(i);
                }
            }
        }

//        System.out.println(st);
        if (st.empty()) longest = s.length();
        else {
            int a = s.length(), b = 0;
            while (!st.empty()) {
                b = st_index.pop();
                st.pop();
                longest = Math.max(longest, a-b-1);
                a = b;
            }
            longest = Math.max(longest, a);
        }
        return longest;
    }

    /**
     * longest[i]: 表示到第i索引处的 longest valid parentheses 长度
     * -> if s[i] == '(': longest[i] == 0
     * -> else if s[i] == ')':
     *    -> if s[i-1] == '(': longest[i] = longest[i-2] + 2
     *    -> else if s[i-1] == ')' and s[i-longest[i-1]-1] == '(': longest[i] = longest[i-1] + 2 + longest[i-2-longest[i-1]]
     *    -> else: longest[i] = 0
     * @param s 要查找的字符串
     */
    private static int dp(String s) {
        if (s.length() <= 1) {
            return 0;
        }
        int[] longest = new int[s.length()];
        longest[0] = 0;
        for(int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') longest[i] = 0;
            else {
                if (s.charAt(i-1) == '(') longest[i] = (i-2 >= 0 ? longest[i-2] : 0) + 2;
                else if (s.charAt(i-1) == ')' && i-longest[i-1]-1 >= 0 && s.charAt(i-longest[i-1]-1) == '(') {
                    longest[i] = longest[i-1] + 2 + (i-2-longest[i-1] >= 0 ? longest[i-2-longest[i-1]] : 0);
                } else {
                    longest[i] = 0;
                }
            }
        }

        int longest_length = longest[0];
        for(int i = 1; i < longest.length; i++) {
            if (longest[i] > longest_length) longest_length = longest[i];
        }
        return longest_length;
    }
}
