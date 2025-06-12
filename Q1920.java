package LeetCodes;
/*Given a zero-based permutation nums (0-indexed), build an array ans of the same length where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).
Example 1:
Input: nums = [0,2,1,5,3,4]
Output: [0,1,2,4,5,3]
Explanation: The array ans is built as follows: 
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
    = [0,1,2,4,5,3]
Example 2:
Input: nums = [5,0,1,2,3,4]
Output: [4,5,0,1,2,3]
Explanation: The array ans is built as follows:
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums5]]]
    = [nums[5], nums[0], nums[1], nums[2], nums[3], nums[4]]
    = [4,5,0,1,2,3]*/
import java.util.Scanner;

class Q1920 {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        
        // Iterate through the array to build the result
        for (int i = 0; i < n; i++) {
            ans[i] = nums[nums[i]];
        }
        
        return ans;
    }

    public static void main(String[] args) {
        // Create Scanner object for taking input
        Scanner scanner = new Scanner(System.in);

        // Take the input for the array
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];

        System.out.println("Enter the elements of the array (distinct integers from 0 to " + (n - 1) + "):");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Create an instance of Q1920 class and call buildArray method
        Q1920 solution = new Q1920();
        int[] ans = solution.buildArray(nums);

        // Print the resulting array
        System.out.println("Resulting array: ");
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }
}
