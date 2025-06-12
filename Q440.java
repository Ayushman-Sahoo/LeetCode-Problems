package LeetCodes;
/*Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
Example 1:
Input: n = 13, k = 2
Output: 10
Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
Example 2:
Input: n = 1, k = 1
Output: 1*/
import java.util.Scanner;

public class Q440 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Take input for n and k
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        System.out.print("Enter k: ");
        int k = sc.nextInt();

        // Create object and call the method
        Q440 obj = new Q440();
        int result = obj.findKthNumber(n, k);
        
        // Output the result
        System.out.println("The " + k + "th lexicographical number is: " + result);
        
        sc.close();
    }

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--; // we start from 1, so we decrement k

        while (k > 0) {
            long steps = countSteps(n, curr, curr + 1);
            if (steps <= k) {
                curr++;     // move to next sibling
                k -= steps; // skip `steps` numbers
            } else {
                curr *= 10; // move to first child
                k--;        // move to next number
            }
        }
        return curr;
    }

    private long countSteps(int n, long first, long last) {
        long steps = 0;
        while (first <= n) {
            steps += Math.min(n + 1, last) - first;
            first *= 10;
            last *= 10;
        }
        return steps;
    }
}
