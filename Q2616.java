package LeetCodes;
/*You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized. Also, ensure no index appears more than once amongst the p pairs.
Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.
Return the minimum maximum difference among all p pairs. We define the maximum of an empty set to be zero.
Example 1:
Input: nums = [10,1,2,7,1,3], p = 2
Output: 1
Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5. 
The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.
Example 2:
Input: nums = [4,2,1,2], p = 1
Output: 0
Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.*/
import java.util.*;

public class Q2616 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read input array
        System.out.print("Enter number of elements in the array: ");
        int n = sc.nextInt();
        int[] nums = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Read number of pairs
        System.out.print("Enter the number of pairs (p): ");
        int p = sc.nextInt();

        // Solve and print result
        Q2616 obj = new Q2616();
        int result = obj.minimizeMax(nums, p);
        System.out.println("Minimum maximum difference among all pairs: " + result);
    }

    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length - 1] - nums[0];
        while (left < right) {
            int mid = (left + right) / 2;
            if (canFormPairs(nums, p, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    private boolean canFormPairs(int[] nums, int p, int maxDiff) {
        int count = 0;
        for (int i = 1; i < nums.length; ) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                count++;
                i += 2; // use both indices, so skip next
            } else {
                i += 1;
            }
        }
        return count >= p;
    }
}