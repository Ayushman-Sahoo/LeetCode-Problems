package LeetCodes;
/*You are given a string s consisting of lowercase English letters, an integer t representing the number of transformations to perform, and an array nums of size 26. In one transformation, every character in s is replaced according to the following rules:
Replace s[i] with the next nums[s[i] - 'a'] consecutive characters in the alphabet. For example, if s[i] = 'a' and nums[0] = 3, the character 'a' transforms into the next 3 consecutive characters ahead of it, which results in "bcd".
The transformation wraps around the alphabet if it exceeds 'z'. For example, if s[i] = 'y' and nums[24] = 3, the character 'y' transforms into the next 3 consecutive characters ahead of it, which results in "zab".
Return the length of the resulting string after exactly t transformations.
Since the answer may be very large, return it modulo 109 + 7.
Example 1:
Input: s = "abcyy", t = 2, nums = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2]
Output: 7
Explanation:
First Transformation (t = 1):
'a' becomes 'b' as nums[0] == 1
'b' becomes 'c' as nums[1] == 1
'c' becomes 'd' as nums[2] == 1
'y' becomes 'z' as nums[24] == 1
'y' becomes 'z' as nums[24] == 1
String after the first transformation: "bcdzz"
Second Transformation (t = 2):
'b' becomes 'c' as nums[1] == 1
'c' becomes 'd' as nums[2] == 1
'd' becomes 'e' as nums[3] == 1
'z' becomes 'ab' as nums[25] == 2
'z' becomes 'ab' as nums[25] == 2
String after the second transformation: "cdeabab"
Final Length of the string: The string is "cdeabab", which has 7 characters.
Example 2:
Input: s = "azbk", t = 1, nums = [2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]
Output: 8 */
import java.util.*;

public class Q3337 {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt for string s
        System.out.println("Enter the string s (only lowercase letters):");
        String s = sc.nextLine().trim();

        // Prompt for number of transformations t
        System.out.println("Enter the number of transformations (t):");
        long t = sc.nextLong();
        sc.nextLine(); // Consume leftover newline

        // Prompt for the nums array
        System.out.println("Enter 26 space-separated integers for nums array (nums[0] for 'a', ..., nums[25] for 'z'):");
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            nums.add(sc.nextInt());
        }

        Q3337 solver = new Q3337();
        int result = solver.lengthAfterTransformations(s, t, nums);
        System.out.println("Length of resulting string after " + t + " transformations: " + result);
    }

    public int lengthAfterTransformations(String s, long t, List<Integer> nums) {
        int[][] M = new int[26][26];

        // Build transformation matrix M
        for (int i = 0; i < 26; i++) {
            int count = nums.get(i);
            for (int j = 1; j <= count; j++) {
                int to = (i + j) % 26;
                M[i][to] = (M[i][to] + 1) % MOD;
            }
        }

        // Raise M to the power t
        int[][] Mt = matrixPower(M, t);

        // Frequency vector of input string
        long[] freq = new long[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Multiply freq × Mt
        long[] result = new long[26];
        for (int j = 0; j < 26; j++) {
            for (int i = 0; i < 26; i++) {
                result[j] = (result[j] + freq[i] * Mt[i][j]) % MOD;
            }
        }

        // Sum all transformed characters
        long total = 0;
        for (long val : result) {
            total = (total + val) % MOD;
        }

        return (int) total;
    }

    // Matrix exponentiation
    private int[][] matrixPower(int[][] mat, long power) {
        int size = mat.length;
        int[][] res = new int[size][size];

        // Identity matrix
        for (int i = 0; i < size; i++) res[i][i] = 1;

        while (power > 0) {
            if ((power & 1) == 1) res = multiply(res, mat);
            mat = multiply(mat, mat);
            power >>= 1;
        }
        return res;
    }

    // Matrix multiplication
    private int[][] multiply(int[][] A, int[][] B) {
        int size = A.length;
        int[][] C = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    C[i][j] = (int)((C[i][j] + (long)A[i][k] * B[k][j]) % MOD);
                }
            }
        }
        return C;
    }
}
