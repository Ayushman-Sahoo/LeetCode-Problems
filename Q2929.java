package LeetCodes;
/*You are given two positive integers n and limit.
Return the total number of ways to distribute n candies among 3 children such that no child gets more than limit candies.
Example 1:
Input: n = 5, limit = 2
Output: 3
Explanation: There are 3 ways to distribute 5 candies such that no child gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
Example 2:
Input: n = 3, limit = 3
Output: 10
Explanation: There are 10 ways to distribute 3 candies such that no child gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), (2, 1, 0) and (3, 0, 0).*/
import java.util.Scanner;

public class Q2929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read input
        System.out.print("Enter total candies (n): ");
        int n = sc.nextInt();

        System.out.print("Enter candy limit per child: ");
        int limit = sc.nextInt();

        // Solve and print result
        System.out.println("Number of valid distributions: " + distributeCandies(n, limit));
    }

    public static long distributeCandies(int n, int limit) {
        return countWays(n, limit);
    }

    private static long countWays(int n, int limit) {
        long total = nCk(n + 2, 2);

        long overA = (n - (limit + 1) >= 0) ? nCk(n - (limit + 1) + 2, 2) : 0;
        long overB = overA; // same as overA
        long overC = overA;

        long overAB = (n - 2 * (limit + 1) >= 0) ? nCk(n - 2 * (limit + 1) + 2, 2) : 0;
        long overAC = overAB;
        long overBC = overAB;

        long overABC = (n - 3 * (limit + 1) >= 0) ? nCk(n - 3 * (limit + 1) + 2, 2) : 0;

        return total - (overA + overB + overC) + (overAB + overAC + overBC) - overABC;
    }

    // Computes n choose k (nCk)
    private static long nCk(int n, int k) {
        if (n < k || k < 0) return 0;
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }
}
