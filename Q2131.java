package LeetCodes;
/*You are given an array of strings words. Each element of words consists of two lowercase English letters.
Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.
Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
A palindrome is a string that reads the same forward and backward.
Example 1:
Input: words = ["lc","cl","gg"]
Output: 6
Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
Note that "clgglc" is another longest palindrome that can be created.
Example 2:
Input: words = ["ab","ty","yt","lc","cl","ab"]
Output: 8
Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
Note that "lcyttycl" is another longest palindrome that can be created.
Example 3:
Input: words = ["cc","ll","xx"]
Output: 2
Explanation: One longest palindrome is "cc", of length 2.
Note that "ll" is another longest palindrome that can be created, and so is "xx". */
import java.util.*;

public class Q2131 {
    public static int longestPalindrome(String[] words) {
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        int length = 0;
        boolean hasCenter = false;

        for (String word : freq.keySet()) {
            String reversed = new StringBuilder(word).reverse().toString();
            int count = freq.get(word);

            if (word.equals(reversed)) {
                length += (count / 2) * 4;
                if (count % 2 == 1) {
                    hasCenter = true;
                }
            } else if (freq.containsKey(reversed)) {
                int min = Math.min(count, freq.get(reversed));
                length += min * 4;
                freq.put(word, 0);
                freq.put(reversed, 0);
            }
        }

        if (hasCenter) {
            length += 2;
        }

        return length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of words:");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        String[] words = new String[n];
        System.out.println("Enter the words (each of two lowercase letters):");
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine().trim();
        }

        int result = longestPalindrome(words);
        System.out.println("Length of the longest palindrome: " + result);
    }
}