package LeetCodes;
/*There is a dungeon with n x m rooms arranged as a grid.
You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.
Return the minimum time to reach the room (n - 1, m - 1).
Two rooms are adjacent if they share a common wall, either horizontally or vertically.
Example 1:
Input: moveTime = [[0,4],[4,4]]
Output: 6
Explanation:
The minimum time required is 6 seconds.
At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in one second.
Example 2:
Input: moveTime = [[0,0,0],[0,0,0]]
Output: 3
Explanation:
The minimum time required is 3 seconds.
At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in one second.
At time t == 2, move from room (1, 1) to room (1, 2) in one second.
Example 3:
Input: moveTime = [[0,1],[1,2]]
Output: 3 */
import java.util.*;

public class Q3341 {

    static class Cell implements Comparable<Cell> {
        int time, row, col;

        Cell(int time, int row, int col) {
            this.time = time;
            this.row = row;
            this.col = col;
        }

        public int compareTo(Cell other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public static int minimumTime(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visited = new boolean[n][m];

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        pq.offer(new Cell(0, 0, 0)); // Start from (0, 0) at time 0

        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            int time = current.time, row = current.row, col = current.col;

            if (visited[row][col]) continue;
            visited[row][col] = true;

            if (row == n - 1 && col == m - 1) return time;

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && !visited[newRow][newCol]) {
                    int nextTime = time + 1;
                    if (nextTime < moveTime[newRow][newCol]) {
                        // Wait until the minimum move time
                        int waitTime = moveTime[newRow][newCol];
                        // If parity doesn't match, wait one more second
                        if ((waitTime - nextTime) % 2 == 1) waitTime++;
                        nextTime = waitTime;
                    }
                    pq.offer(new Cell(nextTime, newRow, newCol));
                }
            }
        }

        return -1; // Should not happen for a valid grid
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows (n): ");
        int n = sc.nextInt();
        System.out.print("Enter number of columns (m): ");
        int m = sc.nextInt();

        int[][] moveTime = new int[n][m];
        System.out.println("Enter the moveTime matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                moveTime[i][j] = sc.nextInt();
            }
        }

        int result = minimumTime(moveTime);
        System.out.println("Minimum time to reach destination: " + result);
    }
}
