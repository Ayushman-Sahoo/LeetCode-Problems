package LeetCodes;
/*You are given an integer array nums of size n where n is a multiple of 3 and a positive integer k.
Divide the array nums into n / 3 arrays of size 3 satisfying the following condition:
The difference between any two elements in one array is less than or equal to k.
Return a 2D array containing the arrays. If it is impossible to satisfy the conditions, return an empty array. And if there are multiple answers, return any of them.
Example 1:
Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
Output: [[1,1,3],[3,4,5],[7,8,9]]
Explanation:
The difference between any two elements in each array is less than or equal to 2.
Example 2:
Input: nums = [2,4,2,2,5,2], k = 2
Output: []
Explanation:
Different ways to divide nums into 2 arrays of size 3 are:
[[2,2,2],[2,4,5]] (and its permutations)
[[2,2,4],[2,2,5]] (and its permutations)
Because there are four 2s there will be an array with the elements 2 and 5 no matter how we divide it. since 5 - 2 = 3 > k, the condition is not satisfied and so there is no valid division.
Example 3:
Input: nums = [4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11], k = 14
Output: [[2,2,12],[4,8,5],[5,9,7],[7,8,5],[5,9,10],[11,12,2]]
Explanation:
The difference between any two elements in each array is less than or equal to 14.*/
import java.util.*;

public class Q2966 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking input for array
        System.out.print("Enter the number of elements (must be multiple of 3): ");
        int n = sc.nextInt();
        if (n % 3 != 0) {
            System.out.println("[]");
            return;
        }

        int[] nums = new int[n];
        System.out.println("Enter the " + n + " elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Taking input for k
        System.out.print("Enter the value of k: ");
        int k = sc.nextInt();

        // Calling the function and printing result
        int[][] result = divideArray(nums, k);
        if (result.length == 0) {
            System.out.println("[]");
        } else {
            System.out.println("Output:");
            for (int[] triplet : result) {
                System.out.println(Arrays.toString(triplet));
            }
        }
    }

    // Function to divide array into valid triplets
    public static int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i += 3) {
            int a = nums[i], b = nums[i + 1], c = nums[i + 2];
            if (c - a > k) {
                return new int[0][];
            }
            result.add(new int[]{a, b, c});
        }

        return result.toArray(new int[0][]);
    }
}
