package LeetCodes;
/*You are given a string s and an integer k. Your task is to find the maximum difference between the frequency of two characters, freq[a] - freq[b], in a substring subs of s, such that:
subs has a size of at least k.
Character a has an odd frequency in subs.
Character b has an even frequency in subs.
Return the maximum difference.
Note that subs can contain more than 2 distinct characters.
Example 1:
Input: s = "12233", k = 4
Output: -1
Explanation:
For the substring "12233", the frequency of '1' is 1 and the frequency of '3' is 2. The difference is 1 - 2 = -1.
Example 2:
Input: s = "1122211", k = 3
Output: 1
Explanation:
For the substring "11222", the frequency of '2' is 3 and the frequency of '1' is 2. The difference is 3 - 2 = 1.
Example 3:
Input: s = "110", k = 3
Output: -1*/
import java.util.*;

public class Q3445 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Input string
        System.out.print("Enter the string: ");
        String s = sc.next();
        
        // Input k
        System.out.print("Enter the value of k: ");
        int k = sc.nextInt();
        
        Solution sol = new Solution();
        int result = sol.maxFreqDifference(s, k);
        System.out.println("Maximum difference: " + result);
    }
}

class Solution {
    public int maxFreqDifference(String s, int k) {
        int n = s.length();
        int maxDiff = Integer.MIN_VALUE;
        
        for (int start = 0; start <= n - k; start++) {
            int[] freq = new int[26];
            for (int end = start; end < n; end++) {
              freq[s.charAt(end) - 'a']++;
                if (end - start + 1 >= k) {
                  for (int i = 0; i < 26; i++) {
                    if (freq[i] % 2 == 1) { // odd frequency for a
                      for (int j = 0; j < 26; j++) {
                        if (freq[j] > 0 && freq[j] % 2 == 0) { // even frequency for b
                            int diff = freq[i] - freq[j];
                              maxDiff = Math.max(maxDiff, diff);
                            }
                          }
                        }
                    }
                }
            }
        }
    return maxDiff == Integer.MIN_VALUE ? -1 : maxDiff;
  }
}
