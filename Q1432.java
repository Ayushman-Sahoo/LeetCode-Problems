package LeetCodes;
/*You are given an integer num. You will apply the following steps to num two separate times:
Pick a digit x (0 <= x <= 9).
Pick another digit y (0 <= y <= 9). Note y can be equal to x.
Replace all the occurrences of x in the decimal representation of num by y.
Let a and b be the two results from applying the operation to num independently.
Return the max difference between a and b.
Note that neither a nor b may have any leading zeros, and must not be 0.
Example 1:
Input: num = 555
Output: 888
Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
The second time pick x = 5 and y = 1 and store the new integer in b.
We have now a = 999 and b = 111 and max difference = 888
Example 2:
Input: num = 9
Output: 8
Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
The second time pick x = 9 and y = 1 and store the new integer in b.
We have now a = 9 and b = 1 and max difference = 8*/
import java.util.Scanner;

public class Q1432 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt the user
        System.out.print("Enter an integer number: ");
        int num = sc.nextInt(); // Take input

        // Calculate and display the result
        int result = maxDiff(num);
        System.out.println("Maximum difference after two remappings: " + result);
    }

    public static int maxDiff(int num) {
        String s = String.valueOf(num);

        // For maximum value: replace the first digit that is not '9' with '9'
        char[] maxChars = s.toCharArray();
        char toReplaceMax = 0;
        for (char c : maxChars) {
            if (c != '9') {
                toReplaceMax = c;
                break;
            }
        }
        String maxStr = s;
        if (toReplaceMax != 0) {
            maxStr = s.replace(toReplaceMax, '9');
        }

        // For minimum value
        char[] minChars = s.toCharArray();
        char toReplaceMin = 0;
        char replaceWith = '1';
        if (minChars[0] != '1') {
            toReplaceMin = minChars[0];
            replaceWith = '1';
        } else {
            for (int i = 1; i < minChars.length; i++) {
                if (minChars[i] != '0' && minChars[i] != '1') {
                    toReplaceMin = minChars[i];
                    replaceWith = '0';
                    break;
                }
            }
        }
        String minStr = s;
        if (toReplaceMin != 0) {
            minStr = s.replace(toReplaceMin, replaceWith);
        }
        int max = Integer.parseInt(maxStr);
        int min = Integer.parseInt(minStr);
        return max - min;
    }
}
