package LeetCodes;
/*In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.
If it cannot be done, return -1.
Example 1:
Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
Output: 2
Explanation: 
The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:
Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
Output: -1
Explanation: 
In this case, it is not possible to rotate the dominoes to make one row of values equal.*/ 
import java.util.*;

public class Q1007 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter elements of tops array (space-separated): ");
            int[] tops = parseInput(scanner.nextLine());

            System.out.print("Enter elements of bottoms array (space-separated): ");
            int[] bottoms = parseInput(scanner.nextLine());

            if (tops.length != bottoms.length) {
                throw new IllegalArgumentException("Tops and bottoms must have the same length.");
            }

            int result = minDominoRotations(tops, bottoms);
            System.out.println("Minimum rotations: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter integers only.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Parse user input string into int array
    private static int[] parseInput(String input) throws NumberFormatException {
        String[] parts = input.trim().split("\\s+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
            if (arr[i] < 1 || arr[i] > 6) {
                throw new IllegalArgumentException("Domino values must be between 1 and 6.");
            }
        }
        return arr;
    }

    // Main logic for solving the domino rotations problem
    public static int minDominoRotations(int[] tops, int[] bottoms) {
        int result = check(tops[0], tops, bottoms);
        if (result != -1 || tops[0] == bottoms[0]) return result;
        return check(bottoms[0], tops, bottoms);
    }

    // Helper function to check the minimum rotations to make all values equal to target
    private static int check(int target, int[] tops, int[] bottoms) {
        int topRotations = 0;
        int bottomRotations = 0;
        int n = tops.length;

        for (int i = 0; i < n; i++) {
            if (tops[i] != target && bottoms[i] != target) {
                return -1;
            } else if (tops[i] != target) {
                topRotations++;
            } else if (bottoms[i] != target) {
                bottomRotations++;
            }
        }

        return Math.min(topRotations, bottomRotations);
    }
}