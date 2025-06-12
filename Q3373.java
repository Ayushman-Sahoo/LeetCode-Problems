package LeetCodes;
/*There exist two undirected trees with n and m nodes, labeled from [0, n - 1] and [0, m - 1], respectively.
You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
Node u is target to node v if the number of edges on the path from u to v is even. Note that a node is always target to itself.
Return an array of n integers answer, where answer[i] is the maximum possible number of nodes that are target to node i of the first tree if you had to connect one node from the first tree to another node in the second tree.
Note that queries are independent from each other. That is, for every query you will remove the added edge before proceeding to the next query.
Example 1:
Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]
Output: [8,7,7,8,8]
Explanation:
For i = 0, connect node 0 from the first tree to node 0 from the second tree.
For i = 1, connect node 1 from the first tree to node 4 from the second tree.
For i = 2, connect node 2 from the first tree to node 7 from the second tree.
For i = 3, connect node 3 from the first tree to node 0 from the second tree.
For i = 4, connect node 4 from the first tree to node 4 from the second tree.
Example 2:
Input: edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]]
Output: [3,6,6,6,6]
Explanation:
For every i, connect node i of the first tree with any node of the second tree.*/
import java.util.*;

public class Q3373 {

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        List<Integer>[] tree1 = buildTree(n, edges1);
        List<Integer>[] tree2 = buildTree(m, edges2);

        // --- Optimized calculation for parity counts in tree1 ---
        // Perform a single BFS from an arbitrary root (node 0) to get depths of all nodes.
        // The parity of distance between any two nodes u and v is (depth[u] + depth[v]) % 2.
        int[] depth1 = bfs(tree1, 0); 
        int totalEvenDepth1 = 0;
        int totalOddDepth1 = 0;
        for (int d : depth1) {
            if (d % 2 == 0) {
                totalEvenDepth1++;
            } else {
                totalOddDepth1++;
            }
        }

        // --- Optimized calculation for parity counts in tree2 ---
        // Similarly, perform a single BFS from an arbitrary root (node 0) for tree2.
        int[] depth2 = bfs(tree2, 0); 
        int totalEvenDepth2 = 0;
        int totalOddDepth2 = 0;
        for (int d : depth2) {
            if (d % 2 == 0) {
                totalEvenDepth2++;
            } else {
                totalOddDepth2++;
            }
        }

        // Determine `maxOddCountFromT2ToConnectedNode`.
        // When we connect node 'i' from tree1 to node 'j' from tree2,
        // for any node 'y' in tree2, the path length to 'i' becomes `dist2(y, j) + 1`.
        // For this path length to be even, `dist2(y, j)` must be odd.
        // We want to maximize the number of such 'y' nodes.
        // If we choose an even-depth node 'j' from tree2 to connect:
        //   `dist2(y, j)` is odd if `depth2(y)` is odd. So, `totalOddDepth2` nodes.
        // If we choose an odd-depth node 'j' from tree2 to connect:
        //   `dist2(y, j)` is odd if `depth2(y)` is even. So, `totalEvenDepth2` nodes.
        // To maximize, we pick the larger of `totalEvenDepth2` and `totalOddDepth2`.
        int maxOddCountFromT2ToConnectedNode = Math.max(totalEvenDepth2, totalOddDepth2);

        int[] answer = new int[n];

        // For each node 'i' in the first tree:
        // Calculate the maximum possible number of nodes that are target to 'i'.
        for (int i = 0; i < n; i++) {
            int nodesTargetInT1;
            // Nodes 'x' within tree1 are target to 'i' if `dist1(x, i)` is even.
            // Based on the root (node 0) of tree1:
            // If `depth1[i]` is even, nodes with even depth from root 0 will have an even distance to 'i'.
            // If `depth1[i]` is odd, nodes with odd depth from root 0 will have an even distance to 'i'.
            if (depth1[i] % 2 == 0) {
                nodesTargetInT1 = totalEvenDepth1;
            } else {
                nodesTargetInT1 = totalOddDepth1;
            }

            // The total count for `answer[i]` is the sum of:
            // 1. Nodes in tree1 that are target to 'i'.
            // 2. Nodes in tree2 that can be made target to 'i' by choosing the optimal connection node 'j'.
            answer[i] = nodesTargetInT1 + maxOddCountFromT2ToConnectedNode;
        }

        return answer;
    }

    /**
     * Helper method to build an adjacency list representation of a tree.
     * @param numNodes The total number of nodes in the tree.
     * @param edges A 2D array representing the edges of the tree.
     * @return An array of Lists, where each list stores the neighbors of a node.
     */
    private List<Integer>[] buildTree(int numNodes, int[][] edges) {
        List<Integer>[] tree = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }
        return tree;
    }

    /**
     * Helper method to perform Breadth-First Search (BFS) and calculate distances
     * (depths from the start node) for all reachable nodes in a tree.
     * @param tree The adjacency list representation of the tree.
     * @param start The starting node for the BFS.
     * @return An array where `dist[k]` is the distance from `start` to node `k`,
     * or -1 if node `k` is unreachable.
     */
    private int[] bfs(List<Integer>[] tree, int start) {
        int numNodes = tree.length;
        int[] dist = new int[numNodes];
        Arrays.fill(dist, -1); // Initialize distances to -1 (unvisited)
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : tree[u]) {
                if (dist[v] == -1) { // If not visited
                    dist[v] = dist[u] + 1;
                    queue.offer(v);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read edges1
        System.out.println("Enter number of nodes in tree1 (n):");
        int n = scanner.nextInt();
        System.out.println("Enter number of edges in tree1 (n-1):");
        int numEdges1 = scanner.nextInt();
        int[][] edges1 = new int[numEdges1][2];
        System.out.println("Enter edges for tree1 (e.g., 0 1):");
        for (int i = 0; i < numEdges1; i++) {
            edges1[i][0] = scanner.nextInt();
            edges1[i][1] = scanner.nextInt();
        }

        // Read edges2
        System.out.println("Enter number of nodes in tree2 (m):");
        int m = scanner.nextInt();
        System.out.println("Enter number of edges in tree2 (m-1):");
        int numEdges2 = scanner.nextInt();
        int[][] edges2 = new int[numEdges2][2];
        System.out.println("Enter edges for tree2 (e.g., 0 1):");
        for (int i = 0; i < numEdges2; i++) {
            edges2[i][0] = scanner.nextInt();
            edges2[i][1] = scanner.nextInt();
        }

        Q3373 solution = new Q3373();
        int[] result = solution.maxTargetNodes(edges1, edges2);

        System.out.println("Output:");
        System.out.println(Arrays.toString(result));
    }
}