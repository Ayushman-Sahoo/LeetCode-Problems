package LeetCodes;
/*You are given an integer array nums and a positive integer k.
Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
A subarray is a contiguous sequence of elements within an array.
Example 1:
Input: nums = [1,3,2,3,3], k = 2
Output: 6
Explanation: The subarrays that contain the element 3 at least 2 times are: [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].
Example 2:
Input: nums = [1,4,2,1], k = 3
Output: 0
Explanation: No subarray contains the element 4 at least 3 times. */
import java.util.*;

public class Q2962 {

    // Method to count valid subarrays
    public static long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;

        // Step 1: Find the global maximum
        for (int num : nums) {
            max = Math.max(max, num);
        }

        long ans = 0;
        int count = 0;
        int left = 0;

        // Step 2: Use sliding window
        for (int right = 0; right < n; right++) {
            if (nums[right] == max) {
                count++;
            }

            while (count >= k) {
                if (nums[left] == max) {
                    count--;
                }
                left++;
            }

            ans += left;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input: array size
            System.out.print("Enter the number of elements in the array: ");
            int n = scanner.nextInt();

            if (n <= 0) {
                System.out.println("Array size must be a positive integer.");
                return;
            }

            // Input: array elements
            int[] nums = new int[n];
            System.out.println("Enter " + n + " integers (space-separated):");
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }

            // Input: value of k
            System.out.print("Enter the value of k (positive integer): ");
            int k = scanner.nextInt();

            if (k <= 0) {
                System.out.println("k must be a positive integer.");
                return;
            }

            // Output result
            long result = countSubarrays(nums, k);
            System.out.println("Number of valid subarrays: " + result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter only integers.");
        } finally {
            scanner.close();
        }
    }
}
