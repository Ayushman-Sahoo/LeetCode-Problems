package LeetCodes;
/*There exist two undirected trees with n and m nodes, with distinct labels in ranges [0, n - 1] and [0, m - 1], respectively.
You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree. You are also given an integer k.
Node u is target to node v if the number of edges on the path from u to v is less than or equal to k. Note that a node is always target to itself.
Return an array of n integers answer, where answer[i] is the maximum possible number of nodes target to node i of the first tree if you have to connect one node from the first tree to another node in the second tree.
Note that queries are independent from each other. That is, for every query you will remove the added edge before proceeding to the next query.
Example 1:
Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]], k = 2
Output: [9,7,9,8,8]
Explanation:
For i = 0, connect node 0 from the first tree to node 0 from the second tree.
For i = 1, connect node 1 from the first tree to node 0 from the second tree.
For i = 2, connect node 2 from the first tree to node 4 from the second tree.
For i = 3, connect node 3 from the first tree to node 4 from the second tree.
For i = 4, connect node 4 from the first tree to node 4 from the second tree.
Example 2:
Input: edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]], k = 1
Output: [6,3,3,3,3]
Explanation:
For every i, connect node i of the first tree with any node of the second tree.*/
import java.util.*;

public class Q3372 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read n and edges1
        System.out.println("Enter the number of nodes for tree1 (n):");
        int n = scanner.nextInt();
        System.out.println("Enter edges for tree1 (e.g., 0 1, each on a new line, enter -1 to stop):");
        List<int[]> edges1List = new ArrayList<>();
        // In case n=1, there are no edges, so the loop should handle it.
        // It should be n-1 edges. If n=1, n-1=0, so the loop won't run, which is correct.
        for (int i = 0; i < n - 1; i++) {
            int u = scanner.nextInt();
            // Optional: If you want to allow stopping early, you'd need to adjust how n and edges are handled.
            // For a complete tree, you'll always have n-1 edges.
            int v = scanner.nextInt();
            edges1List.add(new int[]{u, v});
        }
        int[][] edges1 = edges1List.toArray(new int[0][]);

        // Read m and edges2
        System.out.println("Enter the number of nodes for tree2 (m):");
        int m = scanner.nextInt();
        // CORRECTED LINE: System.out.println instead of System.println
        System.out.println("Enter edges for tree2 (e.g., 0 1, each on a new line, enter -1 to stop):");
        List<int[]> edges2List = new ArrayList<>();
        // In case m=1, there are no edges, so the loop should handle it.
        for (int i = 0; i < m - 1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges2List.add(new int[]{u, v});
        }
        int[][] edges2 = edges2List.toArray(new int[0][]);

        // Read k
        System.out.println("Enter the value for k:");
        int k = scanner.nextInt();

        scanner.close();

        Solution solution = new Solution();
        int[] result = solution.maxTargetNodes(edges1, edges2, k);

        System.out.println("Result:");
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // The Solution class remains the same as provided in the previous optimized version
    static class Solution {
        public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
            int n = edges1.length + 1;
            int m = edges2.length + 1;

            List<Integer>[] tree1 = buildTree(n, edges1);
            List<Integer>[] tree2 = buildTree(m, edges2);

            // Precompute node counts at each distance 0...k from every node in tree1 and tree2
            // distCountTreeX[node][distance] stores the number of nodes at 'distance' from 'node'
            int[][] distCountTree1 = new int[n][k + 1];
            int[][] distCountTree2 = new int[m][k + 1];

            for (int i = 0; i < n; i++) {
                distCountTree1[i] = bfs(tree1, i, k);
            }
            for (int j = 0; j < m; j++) {
                distCountTree2[j] = bfs(tree2, j, k);
            }

            // Precompute the total number of nodes reachable within tree2 from each node j,
            // considering a path length constraint of k-1 (due to the connecting edge)
            int[] totalReachableInTree2 = new int[m];
            for (int j = 0; j < m; j++) {
                int currentSum = 0;
                // Nodes reachable in tree2 from j must have a distance d_prime <= k - 1
                // because there's already 1 edge (i--j) used.
                // Loop should only go up to k-1. If k-1 is negative, the loop won't run, which is correct for k=0.
                for (int d_prime = 0; d_prime <= k - 1; d_prime++) {
                    // This check (d_prime < distCountTree2[j].length) is actually redundant
                    // because the bfs populates distCountTree2[j] up to k, and d_prime <= k-1
                    // so d_prime will always be within bounds [0, k-1], which is less than k.
                    currentSum += distCountTree2[j][d_prime];
                }
                totalReachableInTree2[j] = currentSum;
            }

            int[] result = new int[n];

            // For each node 'i' in tree1 (the target node for which we want to maximize reachable nodes)
            for (int i = 0; i < n; i++) {
                int maxReachableFor_i = 0;

                // Calculate nodes in tree1 reachable from 'i' directly (within distance k)
                int nodesInTree1ReachableFrom_i = 0;
                for (int d = 0; d <= k; d++) {
                    nodesInTree1ReachableFrom_i += distCountTree1[i][d];
                }

                // Try connecting node 'i' from tree1 to every node 'j' from tree2
                for (int j = 0; j < m; j++) {
                    // Total reachable nodes = (nodes reachable in tree1 from i) + (nodes reachable in tree2 from j via connection)
                    int currentTotal = nodesInTree1ReachableFrom_i + totalReachableInTree2[j];
                    maxReachableFor_i = Math.max(maxReachableFor_i, currentTotal);
                }
                result[i] = maxReachableFor_i;
            }

            return result;
        }

        // Helper to build adjacency list for a tree
        private List<Integer>[] buildTree(int numNodes, int[][] edges) {
            List<Integer>[] graph = new ArrayList[numNodes];
            for (int i = 0; i < numNodes; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            return graph;
        }

        // Helper to perform BFS and count nodes at each distance up to 'k'
        private int[] bfs(List<Integer>[] graph, int start, int k) {
            int[] count = new int[k + 1]; // count[d] stores number of nodes at distance 'd'
            boolean[] visited = new boolean[graph.length];
            Queue<int[]> queue = new LinkedList<>(); // {node, distance}

            queue.offer(new int[]{start, 0});
            visited[start] = true;

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int node = curr[0];
                int dist = curr[1];

                if (dist > k) {
                    continue; // Only interested in distances up to k
                }

                count[dist]++; // Increment count for current distance

                for (int neighbor : graph[node]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(new int[]{neighbor, dist + 1});
                    }
                }
            }
            return count;
        }
    }
}