package LeetCodes;
/*There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
You are given a string dominoes representing the initial state where:
dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.
Example 1:
Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Example 2:
Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.." */
import java.util.Scanner;

public class Q838 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the dominoes string: ");
            String input = scanner.nextLine();
            
            // Check if input string contains only 'L', 'R', or '.'
            if (!input.matches("[LR.]*")) {
                throw new IllegalArgumentException("Invalid input: The string must contain only 'L', 'R', or '.' characters.");
            }

            String result = pushDominoes(input);
            System.out.println("Final state: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] arr = dominoes.toCharArray();
        int i = 0;

        int left = -1;  // Last 'L' index
        int right = -1; // Last 'R' index

        while (i < n) {
            if (arr[i] == 'L') {
                if (right == -1) {
                    // No 'R' before, push all dots to 'L'
                    for (int k = i - 1; k > left; k--) {
                        arr[k] = 'L';
                    }
                } else {
                    // Balance forces between R and L
                    int low = right + 1, high = i - 1;
                    while (low < high) {
                        arr[low++] = 'R';
                        arr[high--] = 'L';
                    }
                    // If they meet in the middle, it stays '.'
                    right = -1;
                }
                left = i;
            } else if (arr[i] == 'R') {
                if (right != -1) {
                    // Fill all between two 'R's
                    for (int k = right + 1; k < i; k++) {
                        arr[k] = 'R';
                    }
                }
                right = i;
            }
            i++;
        }

        // If there's a trailing 'R' without a stopping 'L'
        if (right != -1) {
            for (int k = right + 1; k < n; k++) {
                arr[k] = 'R';
            }
        }
        return new String(arr);
    }
}
