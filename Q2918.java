/*You are given two arrays nums1 and nums2 consisting of positive integers.

You have to replace all the 0's in both arrays with strictly positive integers such that the sum of elements of both arrays becomes equal.

Return the minimum equal sum you can obtain, or -1 if it is impossible.

 

Example 1:

Input: nums1 = [3,2,0,1,0], nums2 = [6,5,0]
Output: 12
Explanation: We can replace 0's in the following way:
- Replace the two 0's in nums1 with the values 2 and 4. The resulting array is nums1 = [3,2,2,1,4].
- Replace the 0 in nums2 with the value 1. The resulting array is nums2 = [6,5,1].
Both arrays have an equal sum of 12. It can be shown that it is the minimum sum we can obtain.
Example 2:

Input: nums1 = [2,0,2,0], nums2 = [1,4]
Output: -1
Explanation: It is impossible to make the sum of both arrays equal. */
import java.util.Scanner;

public class Q2918 {
    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0, sum2 = 0;
        int zeroCount1 = 0, zeroCount2 = 0;

        for (int num : nums1) {
            if (num == 0) zeroCount1++;
            else sum1 += num;
        }
        for (int num : nums2) {
            if (num == 0) zeroCount2++;
            else sum2 += num;
        }

        long diff = sum2 - sum1;

        // Handle special cases when one side has no zeros
        if (zeroCount1 == 0 && zeroCount2 == 0) {
            return sum1 == sum2 ? sum1 : -1;
        }

        if (zeroCount1 == 0) {
            long y = sum1 - sum2;
            if (y < zeroCount2) return -1;
            long equalSum = sum2 + y;
            if (equalSum < sum1 + zeroCount1) return -1;
            if (equalSum < sum2 + zeroCount2) return -1;
            return equalSum;
        }

        if (zeroCount2 == 0) {
            long x = sum2 - sum1;
            if (x < zeroCount1) return -1;
            long equalSum = sum1 + x;
            if (equalSum < sum1 + zeroCount1) return -1;
            if (equalSum < sum2 + zeroCount2) return -1;
            return equalSum;
        }

        long t = Math.max(zeroCount2, zeroCount1 - diff);
        if (t < 0) return -1;

        long equalSum = sum2 + t;

        if (equalSum < sum1 + zeroCount1) return -1;
        if (equalSum < sum2 + zeroCount2) return -1;

        return equalSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input first array
        System.out.println("Enter length of nums1:");
        int n1 = sc.nextInt();
        int[] nums1 = new int[n1];
        System.out.println("Enter elements of nums1:");
        for (int i = 0; i < n1; i++) {
            nums1[i] = sc.nextInt();
        }

        // Input second array
        System.out.println("Enter length of nums2:");
        int n2 = sc.nextInt();
        int[] nums2 = new int[n2];
        System.out.println("Enter elements of nums2:");
        for (int i = 0; i < n2; i++) {
            nums2[i] = sc.nextInt();
        }

        Q2918 solution = new Q2918();
        long result = solution.minSum(nums1, nums2);

        System.out.println("Minimum equal sum or -1 if impossible:");
        System.out.println(result);
    }
}

