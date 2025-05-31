package LeetCodes;
/*You are given a 0-indexed integer array nums of size 3 which can form the sides of a triangle.
A triangle is called equilateral if it has all sides of equal length.
A triangle is called isosceles if it has exactly two sides of equal length.
A triangle is called scalene if all its sides are of different lengths.
Return a string representing the type of triangle that can be formed or "none" if it cannot form a triangle.
Example 1:
Input: nums = [3,3,3]
Output: "equilateral"
Explanation: Since all the sides are of equal length, therefore, it will form an equilateral triangle.
Example 2:
Input: nums = [3,4,5]
Output: "scalene"
Explanation: 
nums[0] + nums[1] = 3 + 4 = 7, which is greater than nums[2] = 5.
nums[0] + nums[2] = 3 + 5 = 8, which is greater than nums[1] = 4.
nums[1] + nums[2] = 4 + 5 = 9, which is greater than nums[0] = 3. 
Since the sum of the two sides is greater than the third side for all three cases, therefore, it can form a triangle.
As all the sides are of different lengths, it will form a scalene triangle.*/
import java.util.Scanner;
import java.util.Arrays;

public class Q3024 {
    public static String triangleType(int[] nums) {
        // Sort the array to simplify triangle inequality check
        Arrays.sort(nums);

        // Triangle inequality: sum of smaller two sides must be greater than the third
        if (nums[0] + nums[1] <= nums[2]) {
            return "none";
        }

        // All sides equal
        if (nums[0] == nums[1] && nums[1] == nums[2]) {
            return "equilateral";
        }

        // Two sides equal
        if (nums[0] == nums[1] || nums[1] == nums[2]) {
            return "isosceles";
        }

        // All sides different
        return "scalene";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[3];

        System.out.println("Enter 3 integers representing the sides of a triangle:");
        for (int i = 0; i < 3; i++) {
            nums[i] = scanner.nextInt();
        }

        String result = triangleType(nums);
        System.out.println("Triangle Type: " + result);
    }
}
