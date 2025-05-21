package LeetCodes; 
/*You have n tasks and m workers. Each task has a strength requirement stored in a 0-indexed integer array tasks, with the ith task requiring tasks[i] strength to complete. The strength of each worker is stored in a 0-indexed integer array workers, with the jth worker having workers[j] strength. Each worker can only be assigned to a single task and must have a strength greater than or equal to the task's strength requirement (i.e., workers[j] >= tasks[i]).
Additionally, you have pills magical pills that will increase a worker's strength by strength. You can decide which workers receive the magical pills, however, you may only give each worker at most one magical pill.
Given the 0-indexed integer arrays tasks and workers and the integers pills and strength, return the maximum number of tasks that can be completed.
Example 1.
Input: tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
Output: 3
Explanation:
We can assign the magical pill and tasks as follows:
- Give the magical pill to worker 0.
- Assign worker 0 to task 2 (0 + 1 >= 1)
- Assign worker 1 to task 1 (3 >= 2)
- Assign worker 2 to task 0 (3 >= 3)
Example 2:
Input: tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
Output: 1
Explanation:
We can assign the magical pill and tasks as follows:
- Give the magical pill to worker 0.
- Assign worker 0 to task 0 (0 + 5 >= 5)
Example 3:
Input: tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
Output: 2
Explanation:
We can assign the magical pills and tasks as follows:
- Give the magical pill to worker 0 and worker 1.
- Assign worker 0 to task 0 (0 + 10 >= 10)
- Assign worker 1 to task 1 (10 + 10 >= 15)
The last pill is not given because it will not make any worker strong enough for the last task. */
import java.util.*;

public class Q2071 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // 1. Number of tasks
            System.out.println("Enter number of tasks:");
            int n = Integer.parseInt(scanner.nextLine());

            // 2. Task strengths
            int[] tasks = new int[n];
            System.out.println("Enter task strengths (space-separated):");
            String[] taskInput = scanner.nextLine().split(" ");
            if (taskInput.length != n) throw new IllegalArgumentException("Task count mismatch.");
            for (int i = 0; i < n; i++) tasks[i] = Integer.parseInt(taskInput[i]);

            // 3. Number of workers
            System.out.println("Enter number of workers:");
            int m = Integer.parseInt(scanner.nextLine());

            // 4. Worker strengths
            int[] workers = new int[m];
            System.out.println("Enter worker strengths (space-separated):");
            String[] workerInput = scanner.nextLine().split(" ");
            if (workerInput.length != m) throw new IllegalArgumentException("Worker count mismatch.");
            for (int i = 0; i < m; i++) workers[i] = Integer.parseInt(workerInput[i]);

            // 5. Number of pills
            System.out.println("Enter number of pills:");
            int pills = Integer.parseInt(scanner.nextLine());

            // 6. Pill strength
            System.out.println("Enter pill strength:");
            int strength = Integer.parseInt(scanner.nextLine());

            // 7. Run the computation
            Q2071 solver = new Q2071();  // Use this class directly
            int result = solver.maxTaskAssign(tasks, workers, pills, strength);
            System.out.println("Maximum tasks assigned: " + result);

        } catch (NumberFormatException e) {
            System.err.println("Invalid number format. Please enter only integers.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);

        int left = 0, right = Math.min(tasks.length, workers.length);
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canAssign(mid, tasks, workers, pills, strength)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean canAssign(int k, int[] tasks, int[] workers, int pills, int strength) {
        TreeMap<Integer, Integer> multiset = new TreeMap<>();
        int n = workers.length;

        for (int i = n - k; i < n; i++) {
            multiset.put(workers[i], multiset.getOrDefault(workers[i], 0) + 1);
        }

        int remainingPills = pills;

        for (int i = k - 1; i >= 0; i--) {
            int task = tasks[i];

            Integer w = multiset.ceilingKey(task);
            if (w != null) {
                removeWorker(multiset, w);
            } else if (remainingPills > 0) {
                w = multiset.ceilingKey(task - strength);
                if (w != null) {
                    removeWorker(multiset, w);
                    remainingPills--;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    private void removeWorker(TreeMap<Integer, Integer> map, int key) {
        if (map.get(key) == 1) {
            map.remove(key);
        } else {
            map.put(key, map.get(key) - 1);
        }
    }
}