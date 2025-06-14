package LeetCodes;
/*You are given an integer num. You know that Bob will sneakily remap one of the 10 possible digits (0 to 9) to another digit.
Return the difference between the maximum and minimum values Bob can make by remapping exactly one digit in num.
Notes:
When Bob remaps a digit d1 to another digit d2, Bob replaces all occurrences of d1 in num with d2.
Bob can remap a digit to itself, in which case num does not change.
Bob can remap different digits for obtaining minimum and maximum values respectively.
The resulting number after remapping can contain leading zeroes.
Example 1:
Input: num = 11891
Output: 99009
Explanation: 
To achieve the maximum value, Bob can remap the digit 1 to the digit 9 to yield 99899.
To achieve the minimum value, Bob can remap the digit 1 to the digit 0, yielding 890.
The difference between these two numbers is 99009.
Example 2:
Input: num = 90
Output: 99
Explanation:
The maximum value that can be returned by the function is 99 (if 0 is replaced by 9) and the minimum value that can be returned by the function is 0 (if 9 is replaced by 0).
Thus, we return 99.*/
import java.util.Scanner;

public class Q2566 {
    public static int minMaxDifference(int num) {
        String s = String.valueOf(num);

        // For max: replace first non-'9' digit with '9'
        char toReplaceMax = 0;
        for (char c : s.toCharArray()) {
            if (c != '9') {
                toReplaceMax = c;
                break;
            }
        }
        String maxStr = (toReplaceMax == 0) ? s : s.replace(toReplaceMax, '9');

        // For min: replace first digit that's not '0' with '0'
        char toReplaceMin = 0;
        for (char c : s.toCharArray()) {
            if (c != '0') {
                toReplaceMin = c;
                break;
            }
        }
        String minStr = (toReplaceMin == 0) ? s : s.replace(toReplaceMin, '0');

        return Integer.parseInt(maxStr) - Integer.parseInt(minStr);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer num: ");
        int num = scanner.nextInt();
        int result = minMaxDifference(num);
        System.out.println("Difference between max and min remapped values: " + result);
        scanner.close();
    }
}
