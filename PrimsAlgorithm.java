package LeetCodes;

import java.util.*;

public class PrimsAlgorithm {

    static class Edge implements Comparable<Edge> {
        int vertex;
        int weight;

        Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void primMST(int[][] graph, int n) {
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        int[] key = new int[n];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;

            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                    pq.offer(new Edge(v, key[v]));
                }
            }
        }

        printMST(parent, graph, n);
    }

    public static void printMST(int[] parent, int[][] graph, int n) {
        int totalWeight = 0;
        System.out.println("Edge \tWeight");
        for (int i = 1; i < n; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
                totalWeight += graph[i][parent[i]];
            } else {
                System.out.println("Node " + i + " is disconnected.");
            }
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("Enter the adjacency matrix:");
        System.out.println("(Use 0 if there is no edge between vertices)");

        for (int i = 0; i < n; i++) {
            System.out.println("Enter row " + (i + 1) + ":");
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        primMST(graph, n);
    }
}
