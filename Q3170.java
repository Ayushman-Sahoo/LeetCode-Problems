package LeetCodes;
/*You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.
While there is a '*', do the following operation:
Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters, you can delete any of them.
Return the lexicographically smallest resulting string after removing all '*' characters.
Example 1:
Input: s = "aaba*"
Output: "aab"
Explanation:
We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes the lexicographically smallest.
Example 2:
Input: s = "abc"
Output: "abc"
Explanation:
There is no '*' in the string.*/
import java.util.*;

public class Q3170 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string s: ");
        String s = sc.next();
        sc.close();

        Q3170 obj = new Q3170();
        String result = obj.removeStarsLexSmall(s);
        System.out.println("Result: " + result);
    }

    public String removeStarsLexSmall(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '*') {
                if (!stack.isEmpty()) {
                    // Find and remove the smallest character in the stack
                    Character minChar = null;
                    for (char ch : stack) {
                        if (minChar == null || ch < minChar) {
                            minChar = ch;
                        }
                    }
                    // Remove only one occurrence (the first smallest from the left)
                    boolean removed = false;
                    Deque<Character> temp = new ArrayDeque<>();
                    while (!stack.isEmpty()) {
                        char top = stack.pollFirst();
                        if (!removed && top == minChar) {
                            removed = true;
                            continue; // skip this character
                        }
                        temp.offerLast(top);
                    }
                    stack = temp;
                }
            } else {
                stack.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }

        return sb.toString();
    }
}
