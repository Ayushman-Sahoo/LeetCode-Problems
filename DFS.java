package LeetCodes;
//Recursive Code 
import java.util.*;

public class DFS {
    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void dfs(int node) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input format:");
        System.out.println("1. Number of vertices");
        System.out.println("2. Number of edges");
        System.out.println("3. Edges (each line: u v, zero-based indices)");
        System.out.println("4. Start node for DFS");

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (u < 0 || u >= n || v < 0 || v >= n) {
                System.out.println("Invalid edge: node index out of range. Exiting.");
                sc.close();
                return;
            }

            graph.get(u).add(v);
            graph.get(v).add(u); // for undirected graph
        }

        visited = new boolean[n];

        System.out.print("Enter start node for DFS: ");
        int start = sc.nextInt();

        if (start < 0 || start >= n) {
            System.out.println("Invalid start node. Exiting.");
            sc.close();
            return;
        }

        System.out.println("DFS traversal (recursive):");
        dfs(start);
        System.out.println();
    }
}

//Non Recursive 
/*import java.util.*;

public class DFS {
    static List<List<Integer>> graph;

    public static void dfs(int start, int n) {
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        System.out.println("DFS traversal (non-recursive):");
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                System.out.print(node + " ");

                // Add neighbors in reverse order for correct traversal
                List<Integer> neighbors = graph.get(node);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int neighbor = neighbors.get(i);
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println(); // newline after traversal
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input format:");
        System.out.println("1. Number of vertices (n)");
        System.out.println("2. Number of edges (m)");
        System.out.println("3. Edges (each line: u v) with 0-based vertex indices");
        System.out.println("4. Start node for DFS");

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (u < 0 || u >= n || v < 0 || v >= n) {
                System.out.println("Invalid edge: vertex out of range. Exiting.");
                sc.close();
                return;
            }

            graph.get(u).add(v);
            graph.get(v).add(u); // for undirected graph
        }

        System.out.print("Enter start node for DFS: ");
        int start = sc.nextInt();

        if (start < 0 || start >= n) {
            System.out.println("Invalid start node. Exiting.");
            sc.close();
            return;
        }

        dfs(start, n);
    }
}*/