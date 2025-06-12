package LeetCodes;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.w, other.w); // Compare edges based on weight
    }
}

public class Kruskal {
    List<Edge> allEdges = new ArrayList<>();
    List<Edge> mst = new ArrayList<>();
    int[] parent, rank;

    public void createGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Vertices:");
        int n = sc.nextInt();
        System.out.println("Enter the number of Edges:");
        int m = sc.nextInt();

        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  // Initialize parent array
            rank[i] = 0;     // Initialize rank array
        }

        System.out.println("Enter the edges (u v w):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            allEdges.add(new Edge(u, v, w));
        }
    }

    public void kruskalMST() {
        allEdges.sort(Comparator.comparingInt(e -> e.w)); // Explicit sorting

        for (Edge edge : allEdges) {
            int rootU = find(edge.u);
            int rootV = find(edge.v);

            if (rootU != rootV) { // If adding this edge won't form a cycle
                mst.add(edge);
                union(rootU, rootV);
            }
        }

        System.out.println("Edges in MST:");
        for (Edge e : mst) {
            System.out.println(e.u + " - " + e.v + " : " + e.w);
        }
    }

    private int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]); // Path compression
        }
        return parent[node];
    }

    private void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }

    public static void main(String[] args) {
        Kruskal g = new Kruskal();
        g.createGraph();
        g.kruskalMST();
    }
}
/*import java.util.*;

public class KruskalMST {
    // Class to represent an edge
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    // Union-Find with path compression and union by rank
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // Path compression
            return parent[x];
        }

        boolean union(int x, int y) {
            int xr = find(x);
            int yr = find(y);
            if (xr == yr)
                return false;

            // Union by rank
            if (rank[xr] < rank[yr]) {
                parent[xr] = yr;
            } else if (rank[xr] > rank[yr]) {
                parent[yr] = xr;
            } else {
                parent[yr] = xr;
                rank[xr]++;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Note: Vertices are numbered from 0 to V-1.");

        // Input number of vertices and edges
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        Edge[] edges = new Edge[E];
        System.out.println("Enter each edge as: src dest weight");

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            if (u < 0 || u >= V || v < 0 || v >= V) {
                System.out.println("Invalid edge: vertices out of range. Exiting.");
                sc.close();
                return;
            }
            edges[i] = new Edge(u, v, w);
        }

        // Sort edges by weight
        Arrays.sort(edges);

        DSU dsu = new DSU(V);
        List<Edge> mst = new ArrayList<>();
        int mstWeight = 0;

        for (Edge e : edges) {
            if (dsu.union(e.src, e.dest)) {
                mst.add(e);
                mstWeight += e.weight;
            }
        }

        System.out.println("\nEdges in the Minimum Spanning Tree:");
        for (Edge e : mst) {
            System.out.println(e.src + " - " + e.dest + " : " + e.weight);
        }
        System.out.println("Total weight of MST: " + mstWeight);
    }
}*/