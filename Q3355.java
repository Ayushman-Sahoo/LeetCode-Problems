package LeetCodes;
/*You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri].
For each queries[i]:
Select a subset of indices within the range [li, ri] in nums.
Decrement the values at the selected indices by 1.
A Zero Array is an array where all elements are equal to 0.
Return true if it is possible to transform nums into a Zero Array after processing all the queries sequentially, otherwise return false.
Example 1:
Input: nums = [1,0,1], queries = [[0,2]]
Output: true
Explanation:
For i = 0:
Select the subset of indices as [0, 2] and decrement the values at these indices by 1.
The array will become [0, 0, 0], which is a Zero Array.
Example 2:
Input: nums = [4,3,2,1], queries = [[1,3],[0,2]]
Output: false
Explanation:
For i = 0:
Select the subset of indices as [1, 2, 3] and decrement the values at these indices by 1.
The array will become [4, 2, 1, 0].
For i = 1:
Select the subset of indices as [0, 1, 2] and decrement the values at these indices by 1.
The array will become [3, 1, 0, 0], which is not a Zero Array.*/
import java.util.Scanner;

public class Q3355 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt user for size of the array
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        int[] nums = new int[n];

        // Prompt user to enter array elements
        System.out.println("Enter " + n + " integers for the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Prompt user for number of queries
        System.out.print("Enter the number of queries: ");
        int q = sc.nextInt();
        int[][] queries = new int[q][2];

        // Prompt user to enter each query range
        System.out.println("Enter the queries (each as two integers li and ri):");
        for (int i = 0; i < q; i++) {
            System.out.print("Query " + (i + 1) + " - Enter li and ri: ");
            queries[i][0] = sc.nextInt(); // li
            queries[i][1] = sc.nextInt(); // ri
        }

        // Call the method to check if it's possible to transform into a Zero Array
        boolean result = isZeroArray(nums, queries);

        // Output result
        System.out.println("Can the array be transformed into a Zero Array? " + (result ? "true" : "false"));
    }

    public static boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] delta = new int[n + 1];

        // Apply range updates using difference array
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            delta[l] += 1;
            if (r + 1 < n) {
                delta[r + 1] -= 1;
            }
        }

        // Prefix sum and validation in one pass
        int current = 0;
        for (int i = 0; i < n; i++) {
            current += delta[i];
            if (nums[i] > current) {
                return false; // Not enough decrements
            }
        }
        return true;
    }
}
