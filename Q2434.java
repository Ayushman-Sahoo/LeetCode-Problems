package LeetCodes;
/*You are given a string s and a robot that currently holds an empty string t. Apply one of the following operations until s and t are both empty:
Remove the first character of a string s and give it to the robot. The robot will append this character to the string t.
Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
Return the lexicographically smallest string that can be written on the paper.
Example 1:
Input: s = "zza"
Output: "azz"
Explanation: Let p denote the written string.
Initially p="", s="zza", t="".
Perform first operation three times p="", s="", t="zza".
Perform second operation three times p="azz", s="", t="".
Example 2:
Input: s = "bac"
Output: "abc"
Explanation: Let p denote the written string.
Perform first operation twice p="", s="c", t="ba". 
Perform second operation twice p="ab", s="c", t="". 
Perform first operation p="ab", s="", t="c". 
Perform second operation p="abc", s="", t="".
Example 3:
Input: s = "bdda"
Output: "addb"
Explanation: Let p denote the written string.
Initially p="", s="bdda", t="".
Perform first operation four times p="", s="", t="bdda".
Perform second operation four times p="addb", s="", t="".*/
import java.util.*;

public class Q2434 {
    public static String robotWithString(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int smallest = 0;

        for (char ch : s.toCharArray()) {
            // Move character from s to t
            stack.push(ch);
            freq[ch - 'a']--;

            // Update the smallest available character in s
            while (smallest < 26 && freq[smallest] == 0) {
                smallest++;
            }

            // Write from t to paper if valid
            while (!stack.isEmpty() && (stack.peek() - 'a') <= smallest) {
                result.append(stack.pop());
            }
        }

        // Write any remaining characters from t
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the string s: ");
        String s = scanner.nextLine();
        String output = robotWithString(s);
        System.out.println("Lexicographically smallest string written on paper: " + output);
    }
}
