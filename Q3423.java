package LeetCodes;
/*Given a circular array nums, find the maximum absolute difference between adjacent elements.
Note: In a circular array, the first and last elements are adjacent.
Example 1:
Input: nums = [1,2,4]
Output: 3
Explanation:
Because nums is circular, nums[0] and nums[2] are adjacent. They have the maximum absolute difference of |4 - 1| = 3.
Example 2:
Input: nums = [-5,-10,-5]
Output: 5
Explanation:
The adjacent elements nums[0] and nums[1] have the maximum absolute difference of |-5 - (-10)| = 5.*/
import java.util.Scanner;

public class Q3423 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking input size
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        Solution sol = new Solution();
        int result = sol.maxAdjacentDistance(nums);
        System.out.println("Maximum absolute difference between adjacent elements: " + result);
    }
}

class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int maxDiff = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums[i] - nums[(i + 1) % n]);
            if (diff > maxDiff) maxDiff = diff;
        }
        return maxDiff;
    }
}
