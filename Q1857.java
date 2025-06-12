package LeetCodes;
/*There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
Example 1:
Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
Example 2:
Input: colors = "a", edges = [[0,0]]
Output: -1
Explanation: There is a cycle from 0 to 0.*/
import java.util.*;

public class Q1857 {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];

        // Initialize graph
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }

        // Build graph and indegree
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }

        // Initialize DP and queue for topological sort
        int[][] dp = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
            dp[i][colors.charAt(i) - 'a'] = 1;
        }

        int visited = 0;
        int maxColorValue = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;

            for (int neighbor : graph.get(node)) {
                for (int c = 0; c < 26; c++) {
                    int add = (colors.charAt(neighbor) - 'a' == c) ? 1 : 0;
                    dp[neighbor][c] = Math.max(dp[neighbor][c], dp[node][c] + add);
                }

                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }

            for (int c = 0; c < 26; c++) {
                maxColorValue = Math.max(maxColorValue, dp[node][c]);
            }
        }

        return visited == n ? maxColorValue : -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Q1857 solution = new Q1857();

        System.out.print("Enter the color string (e.g., abaca): ");
        String colors = scanner.nextLine();
        int n = colors.length();

        System.out.print("Enter the number of edges: ");
        int m = scanner.nextInt();

        int[][] edges = new int[m][2];
        System.out.println("Enter the edges in the format 'from to' (0-indexed):");
        for (int i = 0; i < m; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }

        int result = solution.largestPathValue(colors, edges);
        System.out.println("Output: " + result);
    }
}
