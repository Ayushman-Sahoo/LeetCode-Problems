package LeetCodes;
/*You have two types of tiles: a 2 x 1 domino shape and a tromino shape. 
You may rotate these shapes. Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 10^9 + 7.
In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
Example 1:
Input: n = 3
Output: 5
Explanation: The five different ways are show above.
Example 2:
Input: n = 1
Output: 1*/
import java.util.Scanner;

public class Q790 {

    private static final int MOD = 1_000_000_007;

    public static int numTilings(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        long[] dp = new long[n + 1];
        long[] sum = new long[n + 1];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        sum[0] = 1;
        sum[1] = 2;
        sum[2] = 4;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + sum[i - 3] * 2) % MOD;
            sum[i] = (sum[i - 1] + dp[i]) % MOD;
        }

        return (int) dp[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the value of n: ");
            int n = scanner.nextInt();

            if (n < 0) {
                System.out.println("Please enter a non-negative integer.");
                return;
            }

            int result = numTilings(n);
            System.out.println("Number of ways to tile a 2 x " + n + " board: " + result);
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter an integer.");
        } finally {
            scanner.close();
        }
    }
}