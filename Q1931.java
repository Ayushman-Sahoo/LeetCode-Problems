package LeetCodes;
/*You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.
Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 10^9 + 7.
Example 1:
Input: m = 1, n = 1
Output: 3
Explanation: The three possible colorings are shown in the image above.
Example 2:
Input: m = 1, n = 2
Output: 6
Explanation: The six possible colorings are shown in the image above.
Example 3:
Input: m = 5, n = 5
Output: 580986 */
import java.util.*;

public class Q1931 {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter m (rows): ");
        int m = sc.nextInt();
        System.out.print("Enter n (columns): ");
        int n = sc.nextInt();

        Q1931 solution = new Q1931();
        int result = solution.colorTheGrid(m, n);
        System.out.println("Number of valid colorings: " + result);
    }

    public int colorTheGrid(int m, int n) {
        List<int[]> states = new ArrayList<>();
        generateStates(m, 0, new int[m], states);

        int stateCount = states.size();
        Map<Integer, List<Integer>> transitions = new HashMap<>();

        // Build valid transitions between column states
        for (int i = 0; i < stateCount; i++) {
            for (int j = 0; j < stateCount; j++) {
                if (isValidTransition(states.get(i), states.get(j))) {
                    transitions.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }

        long[] dp = new long[stateCount];
        Arrays.fill(dp, 1); // First column: all valid states allowed

        // Dynamic programming over columns
        for (int col = 1; col < n; col++) {
            long[] newDp = new long[stateCount];
            for (int i = 0; i < stateCount; i++) {
                for (int next : transitions.getOrDefault(i, Collections.emptyList())) {
                    newDp[next] = (newDp[next] + dp[i]) % MOD;
                }
            }
            dp = newDp;
        }

        long result = 0;
        for (long val : dp) {
            result = (result + val) % MOD;
        }

        return (int) result;
    }

    // Generate all valid colorings for one column
    private void generateStates(int m, int row, int[] current, List<int[]> states) {
        if (row == m) {
            states.add(current.clone());
            return;
        }

        for (int color = 0; color < 3; color++) {
            if (row == 0 || current[row - 1] != color) {
                current[row] = color;
                generateStates(m, row + 1, current, states);
            }
        }
    }

    // Ensure two columns are not identical in any row
    private boolean isValidTransition(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) return false;
        }
        return true;
    }
}
