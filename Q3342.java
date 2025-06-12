package LeetCodes;
/*There is a dungeon with n x m rooms arranged as a grid.
You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes one second for one move and two seconds for the next, alternating between the two.
Return the minimum time to reach the room (n - 1, m - 1).
Two rooms are adjacent if they share a common wall, either horizontally or vertically.
Example 1:
Input: moveTime = [[0,4],[4,4]]
Output: 7
Explanation:
The minimum time required is 7 seconds.
At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in two seconds.
Example 2:
Input: moveTime = [[0,0,0,0],[0,0,0,0]]
Output: 6
Explanation:
The minimum time required is 6 seconds.
At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in two seconds.
At time t == 3, move from room (1, 1) to room (1, 2) in one second.
At time t == 4, move from room (1, 2) to room (1, 3) in two seconds.
Example 3:
Input: moveTime = [[0,1],[1,2]]
Output: 4 */
import java.util.*;

public class Q3342 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of rows (n) and columns (m), separated by space:");
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        System.out.println("Enter the moveTime grid (" + n + " rows, each with " + m + " integers):");
        int[][] moveTime = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                moveTime[i][j] = sc.nextInt();
            }
        }
        
        int result = minTimeToReach(moveTime);
        System.out.println("Minimum time to reach the bottom-right room: " + result);
        
        sc.close();
    }
    
    public static int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;

        int[][][] timeReached = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(timeReached[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // (time, x, y, parity)
        pq.offer(new int[]{0, 0, 0, 0});
        timeReached[0][0][0] = 0;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int time = cur[0], x = cur[1], y = cur[2], parity = cur[3];

            if (x == n - 1 && y == m - 1) return time;

            if (time > timeReached[x][y][parity]) continue;

            int moveCost = (parity == 0) ? 1 : 2;

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    int readyAt = moveTime[nx][ny];
                    int startMove = Math.max(time, readyAt);
                    int arriveTime = startMove + moveCost;
                    int nextParity = 1 - parity;

                    if (arriveTime < timeReached[nx][ny][nextParity]) {
                        timeReached[nx][ny][nextParity] = arriveTime;
                        pq.offer(new int[]{arriveTime, nx, ny, nextParity});
                    }
                }
            }
        }

        return -1; // unreachable
    }
}
