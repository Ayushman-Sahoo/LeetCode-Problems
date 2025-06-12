package LeetCodes;
/*You are given a string array words, and an array groups, both arrays having length n.
The hamming distance between two strings of equal length is the number of positions at which the corresponding characters are different.
You need to select the longest subsequence from an array of indices [0, 1, ..., n - 1], such that for the subsequence denoted as [i0, i1, ..., ik-1] having length k, the following holds:
For adjacent indices in the subsequence, their corresponding groups are unequal, i.e., groups[ij] != groups[ij+1], for each j where 0 < j + 1 < k.
words[ij] and words[ij+1] are equal in length, and the hamming distance between them is 1, where 0 < j + 1 < k, for all indices in the subsequence.
Return a string array containing the words corresponding to the indices (in order) in the selected subsequence. If there are multiple answers, return any of them.
Note: strings in words may be unequal in length.
Example 1:
Input: words = ["bab","dab","cab"], groups = [1,2,2]
Output: ["bab","cab"]
Explanation: A subsequence that can be selected is [0,2].
groups[0] != groups[2]
words[0].length == words[2].length, and the hamming distance between them is 1.
So, a valid answer is [words[0],words[2]] = ["bab","cab"].

Another subsequence that can be selected is [0,1].

groups[0] != groups[1]
words[0].length == words[1].length, and the hamming distance between them is 1.
So, another valid answer is [words[0],words[1]] = ["bab","dab"].

It can be shown that the length of the longest subsequence of indices that satisfies the conditions is 2.

Example 2:

Input: words = ["a","b","c","d"], groups = [1,2,3,4]

Output: ["a","b","c","d"]

Explanation: We can select the subsequence [0,1,2,3].

It satisfies both conditions.

Hence, the answer is [words[0],words[1],words[2],words[3]] = ["a","b","c","d"].

It has the longest length among all subsequences of indices that satisfy the conditions.

Hence, it is the only answer.*/
import java.util.*;

public class Q2901 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for input size
        System.out.print("Enter the number of words: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Prompt to enter words
        System.out.println("Enter " + n + " words (one per line):");
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
        }

        // Prompt to enter group IDs
        System.out.println("Enter " + n + " group numbers (space-separated):");
        int[] groups = new int[n];
        for (int i = 0; i < n; i++) {
            groups[i] = scanner.nextInt();
        }

        // Get result and print
        List<String> result = getWordsInLongestSubsequence(words, groups);
        System.out.println("Longest valid subsequence of words:");
        System.out.println(result);
    }

    public static List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        int[] dp = new int[n];       // dp[i] = longest subsequence ending at i
        int[] prev = new int[n];     // prev[i] = previous index in the sequence
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        // DP: Check all j < i to see if we can extend the sequence
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (groups[i] != groups[j] &&
                    words[i].length() == words[j].length() &&
                    isHammingOne(words[i], words[j])) {

                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }

        // Find the end of the longest sequence
        int maxLen = 0, endIdx = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                endIdx = i;
            }
        }

        // Reconstruct the sequence
        LinkedList<String> result = new LinkedList<>();
        while (endIdx != -1) {
            result.addFirst(words[endIdx]);
            endIdx = prev[endIdx];
        }

        return result;
    }

    private static boolean isHammingOne(String a, String b) {
        if (a.length() != b.length()) return false;
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (++diff > 1) return false;
            }
        }
        return diff == 1;
    }
}