package LeetCodes;
/*You are given an integer array digits, where each element is a digit. The array may contain duplicates.
You need to find all the unique integers that follow the given requirements:
The integer consists of the concatenation of three elements from digits in any arbitrary order.
The integer does not have leading zeros.
The integer is even.
For example, if the given digits were [1, 2, 3], integers 132 and 312 follow the requirements.
Return a sorted array of the unique integers.
Example 1:
Input: digits = [2,1,3,0]
Output: [102,120,130,132,210,230,302,310,312,320]
Explanation: All the possible integers that follow the requirements are in the output array. 
Notice that there are no odd integers or integers with leading zeros.
Example 2:
Input: digits = [2,2,8,8,2]
Output: [222,228,282,288,822,828,882]
Explanation: The same digit can be used as many times as it appears in digits. 
In this example, the digit 8 is used twice each time in 288, 828, and 882. 
Example 3:
Input: digits = [3,7,5]
Output: []
Explanation: No even integers can be formed using the given digits.*/
import java.util.*;

public class Q2094 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read input
        System.out.println("Enter number of digits:");
        int n = scanner.nextInt();
        int[] digits = new int[n];
        System.out.println("Enter the digits:");
        for (int i = 0; i < n; i++) {
            digits[i] = scanner.nextInt();
        }

        int[] result = findEvenNumbers(digits);

        // Print the result
        System.out.println("Output:");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static int[] findEvenNumbers(int[] digits) {
        Set<Integer> result = new HashSet<>();

        int len = digits.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (j == i) continue;
                for (int k = 0; k < len; k++) {
                    if (k == i || k == j) continue;

                    int d1 = digits[i], d2 = digits[j], d3 = digits[k];

                    if (d1 == 0) continue;        // skip numbers with leading zero
                    if (d3 % 2 != 0) continue;    // must be even

                    int num = d1 * 100 + d2 * 10 + d3;
                    result.add(num);
                }
            }
        }

        List<Integer> sortedList = new ArrayList<>(result);
        Collections.sort(sortedList);

        int[] resArr = new int[sortedList.size()];
        for (int i = 0; i < sortedList.size(); i++) {
            resArr[i] = sortedList.get(i);
        }

        return resArr;
    }
}