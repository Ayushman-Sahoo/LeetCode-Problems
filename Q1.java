package LeetCodes;
/*Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.
Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:
Input: nums = [3,3], target = 6
Output: [0,1] */
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Q1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Take array size input
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();
        
        // Create array
        int[] nums = new int[n];
        
        // Take array elements input
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        
        // Take target input
        System.out.print("Enter the target: ");
        int target = scanner.nextInt();
        
        // Create an object of Q1
        Q1 obj = new Q1();
        
        // Call twoSum and print the result
        try {
            int[] result = obj.twoSum(nums, target);
            System.out.println("Indices of the two numbers are: " + result[0] + " and " + result[1]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
/*import java.util.Scanner;

class Q1 {
    public int[] twoSum(int[] nums, int target) {
        // Brute-force: check every pair
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take array size input
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        // Create array
        int[] nums = new int[n];

        // Take array elements input
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Take target input
        System.out.print("Enter the target: ");
        int target = scanner.nextInt();

        // Create an object of Q1
        Q1 obj = new Q1();

        // Call twoSum and print the result
        try {
            int[] result = obj.twoSum(nums, target);
            System.out.println("Indices of the two numbers are: " + result[0] + " and " + result[1]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}*/