package LeetCodes;
/*You are given a string s and an integer t, representing the number of transformations to perform. In one transformation, every character in s is replaced according to the following rules:

If the character is 'z', replace it with the string "ab".
Otherwise, replace it with the next character in the alphabet. For example, 'a' is replaced with 'b', 'b' is replaced with 'c', and so on.
Return the length of the resulting string after exactly t transformations.

Since the answer may be very large, return it modulo 109 + 7.
Example 1:
Input: s = "abcyy", t = 2
Output: 7
Explanation:
First Transformation (t = 1):
'a' becomes 'b'
'b' becomes 'c'
'c' becomes 'd'
'y' becomes 'z'
'y' becomes 'z'
String after the first transformation: "bcdzz"
Second Transformation (t = 2):
'b' becomes 'c'
'c' becomes 'd'
'd' becomes 'e'
'z' becomes "ab"
'z' becomes "ab"
String after the second transformation: "cdeabab"
Final Length of the string: The string is "cdeabab", which has 7 characters.
Example 2:
Input: s = "azbk", t = 1
Output: 5
Explanation:
First Transformation (t = 1):
'a' becomes 'b'
'z' becomes "ab"
'b' becomes 'c'
'k' becomes 'l'
String after the first transformation: "babcl"
Final Length of the string: The string is "babcl", which has 5 characters */
import java.util.Scanner;

public class Q3335 {

    public static int lengthAfterTransformations(String s, int t) {
        int MOD = 1_000_000_007;
        long[] count = new long[26];

        // Initialize counts of characters in the original string
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Perform t transformations
        for (int i = 0; i < t; i++) {
            long[] next = new long[26];
            for (int j = 0; j < 26; j++) {
                if (j == 25) { // 'z'
                    next[0] = (next[0] + count[25]) % MOD; // becomes 'a'
                    next[1] = (next[1] + count[25]) % MOD; // becomes 'b'
                } else {
                    next[j + 1] = (next[j + 1] + count[j]) % MOD;
                }
            }
            count = next;
        }

        // Sum all character counts to get final length
        long totalLength = 0;
        for (long c : count) {
            totalLength = (totalLength + c) % MOD;
        }

        return (int) totalLength;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input string and transformations
        System.out.print("Enter string s: ");
        String s = sc.nextLine();

        System.out.print("Enter number of transformations t: ");
        int t = sc.nextInt();

        int result = lengthAfterTransformations(s, t);
        System.out.println("Final length of the string: " + result);
    }
}
