package LeetCodes;
/*You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.
You are also given two integers node1 and node2.
Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.
Note that edges may contain cycles.
Example 1:
Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
Output: 2
Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.
Example 2:
Input: edges = [1,2,-1], node1 = 0, node2 = 2
Output: 2
Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.*/
import java.util.Arrays;
import java.util.Scanner;

public class Q2359 {

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] dist1 = new int[n];
        int[] dist2 = new int[n];

        Arrays.fill(dist1, -1); // Initialize distances to -1 (unreachable)
        Arrays.fill(dist2, -1);

        // DFS to calculate distances from node1
        dfs(node1, edges, dist1, 0);

        // DFS to calculate distances from node2
        dfs(node2, edges, dist2, 0);

        int minMaxDist = Integer.MAX_VALUE;
        int resultNode = -1;

        // Iterate through all nodes to find the meeting node
        for (int i = 0; i < n; i++) {
            // Check if node i is reachable from both node1 and node2
            if (dist1[i] != -1 && dist2[i] != -1) {
                int currentMaxDist = Math.max(dist1[i], dist2[i]);

                if (currentMaxDist < minMaxDist) {
                    minMaxDist = currentMaxDist;
                    resultNode = i;
                } else if (currentMaxDist == minMaxDist) {
                    // If distances are equal, choose the smaller index
                    resultNode = Math.min(resultNode, i);
                }
            }
        }

        return resultNode;
    }

    // Helper DFS function
    private void dfs(int startNode, int[] edges, int[] dist, int currentDistance) {
        // If the node has already been visited or is out of bounds, stop
        // The startNode == -1 check is crucial because edges[i] can be -1,
        // meaning no outgoing edge.
        if (startNode == -1 || dist[startNode] != -1) {
            return;
        }

        dist[startNode] = currentDistance; // Set the distance for the current node

        // Move to the next node if it exists (i.e., edges[startNode] is not -1)
        if (edges[startNode] != -1) {
            dfs(edges[startNode], edges, dist, currentDistance + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of nodes
        System.out.print("Enter the number of nodes (n): ");
        int n = scanner.nextInt();

        // Read the edges array
        int[] edges = new int[n];
        System.out.println("Enter the edges array (n integers separated by spaces, -1 for no outgoing edge):");
        for (int i = 0; i < n; i++) {
            edges[i] = scanner.nextInt();
        }

        // Read node1 and node2
        System.out.print("Enter node1: ");
        int node1 = scanner.nextInt();

        System.out.print("Enter node2: ");
        int node2 = scanner.nextInt();

        Q2359 solution = new Q2359();
        int result = solution.closestMeetingNode(edges, node1, node2);

        System.out.println("The closest meeting node is: " + result);
    }
}