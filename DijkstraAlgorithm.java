package LeetCodes;

import java.util.*;

public class DijkstraAlgorithm {
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Pair implements Comparable<Pair> {
        int node, dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        public int compareTo(Pair other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public static void dijkstra(int n, List<List<Edge>> graph, int source) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.node;

            if (current.dist > dist[u]) continue;

            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        // Output shortest distances
        System.out.println("Shortest distances from node " + source + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("To node " + i + " → " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input format:");
        System.out.println("1. Number of nodes");
        System.out.println("2. Number of edges");
        System.out.println("3. Edges (each line: from to weight)");
        System.out.println("4. Source node");

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter edges (format: from to weight):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            if (u < 0 || u >= n || v < 0 || v >= n) {
                System.out.println("Invalid edge: node index out of range. Exiting.");
                sc.close();
                return;
            }
            graph.get(u).add(new Edge(v, w));
            // For undirected graph, uncomment next line:
            // graph.get(v).add(new Edge(u, w));
        }

        System.out.print("Enter source node: ");
        int source = sc.nextInt();

        if (source < 0 || source >= n) {
            System.out.println("Invalid source node. Exiting.");
            sc.close();
            return;
        }

        dijkstra(n, graph, source);
    }
}
