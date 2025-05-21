package LeetCodes;
/*Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a == c and b == d), or (a == d and b == c) - that is, one domino can be rotated to be equal to another domino.
Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
Example 1:
Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
Output: 1
Example 2:
Input: dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
Output: 3 */
import java.util.*;

public class Q1128 {

    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int[] d : dominoes) {
            // Normalize the domino
            int key = d[0] <= d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            count += map.getOrDefault(key, 0);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        int[][] dominoes = null;

        try {
            System.out.print("Enter number of dominoes: ");
            n = Integer.parseInt(sc.nextLine());

            dominoes = new int[n][2];
            System.out.println("Enter the dominoes (two numbers per line):");

            for (int i = 0; i < n; i++) {
                String[] parts = sc.nextLine().trim().split("\\s+");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Each domino must have exactly two numbers.");
                }

                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);

                if (a < 1 || a > 9 || b < 1 || b > 9) {
                    throw new IllegalArgumentException("Domino numbers must be between 1 and 9.");
                }

                dominoes[i][0] = a;
                dominoes[i][1] = b;
            }

            Q1128 solution = new Q1128();
            int result = solution.numEquivDominoPairs(dominoes);
            System.out.println("Number of equivalent domino pairs: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid integers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}