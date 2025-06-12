package LeetCodes;
//Non Recursive Way
import java.util.*;

public class BFS {
    public static void bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println(); // newline after traversal
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input format:");
        System.out.println("1. Number of nodes (n)");
        System.out.println("2. Number of edges (m)");
        System.out.println("3. Edges (each line: u v) with 0-based node indices");
        System.out.println("4. Starting node for BFS");

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

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
            graph.get(v).add(u); // comment this for directed graph
        }

        System.out.print("Enter starting node for BFS: ");
        int start = sc.nextInt();

        if (start < 0 || start >= n) {
            System.out.println("Invalid start node. Exiting.");
            sc.close();
            return;
        }

        boolean[] visited = new boolean[n];
        System.out.print("BFS Traversal: ");
        bfs(start, graph, visited);
    }
}

//Recursive Way 
/*import java.util.*;

public class BFS {
    public static void bfsRecursive(Queue<Integer> queue, boolean[] visited, List<List<Integer>> graph) {
        if (queue.isEmpty()) return;

        int node = queue.poll();
        System.out.print(node + " ");

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }

        bfsRecursive(queue, visited, graph);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input format:");
        System.out.println("1. Number of nodes (n)");
        System.out.println("2. Number of edges (m)");
        System.out.println("3. Edges (each line: u v) with 0-based node indices");
        System.out.println("4. Starting node for BFS");

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

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
            graph.get(v).add(u); // comment this line if graph is directed
        }

        System.out.print("Enter starting node for BFS: ");
        int start = sc.nextInt();

        if (start < 0 || start >= n) {
            System.out.println("Invalid start node. Exiting.");
            sc.close();
            return;
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        System.out.print("BFS Traversal: ");
        bfsRecursive(queue, visited, graph);
        System.out.println();
    }
}
*/
