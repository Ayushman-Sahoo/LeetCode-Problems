package LeetCodes;
/*You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri].
Each queries[i] represents the following action on nums:
Decrement the value at each index in the range [li, ri] in nums by at most 1.
The amount by which the value is decremented can be chosen independently for each index.
A Zero Array is an array with all its elements equal to 0.
Return the maximum number of elements that can be removed from queries, such that nums can still be converted to a zero array using the remaining queries. If it is not possible to convert nums to a zero array, return -1.
Example 1:
Input: nums = [2,0,2], queries = [[0,2],[0,2],[1,1]]
Output: 1
Explanation:
After removing queries[2], nums can still be converted to a zero array.
Using queries[0], decrement nums[0] and nums[2] by 1 and nums[1] by 0.
Using queries[1], decrement nums[0] and nums[2] by 1 and nums[1] by 0.
Example 2:
Input: nums = [1,1,1,1], queries = [[1,3],[0,2],[1,3],[1,2]]
Output: 2
Explanation:
We can remove queries[2] and queries[3].
Example 3:
Input: nums = [1,2,3,4], queries = [[0,3]]
Output: -1
Explanation:
nums cannot be converted to a zero array even after using all the queries. */
import java.util.*;

public class Q3362 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt and read nums array
        System.out.print("Enter size of nums array: ");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter " + n + " elements of nums array:");
        for(int i = 0; i < n; i++) nums[i] = sc.nextInt();

        // Prompt and read queries
        System.out.print("Enter number of queries: ");
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        System.out.println("Enter each query as two space-separated integers [li ri]:");
        for(int i = 0; i < q; i++) {
            System.out.print("Query " + (i + 1) + ": ");
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }

        // Call the main function and print result
        int result = maxRemovableQueries(nums, queries);
        System.out.println("Maximum number of queries that can be removed: " + result);
    }

    // Main function
    public static int maxRemovableQueries(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int low = 0, high = m, ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (canMakeZero(nums, queries, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Check if array can be reduced to zero with m-k queries
    private static boolean canMakeZero(int[] nums, int[][] queries, int k) {
        int n = nums.length, m = queries.length;

        int[] diff = new int[n + 2];
        for (int i = 0; i < m - k; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            diff[l] += 1;
            if (r + 1 < n) diff[r + 1] -= 1;
        }

        int[] apply = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            apply[i] = sum;
        }

        for (int i = 0; i < n; i++) {
            if (apply[i] < nums[i]) return false;
        }
        return true;
    }
}
