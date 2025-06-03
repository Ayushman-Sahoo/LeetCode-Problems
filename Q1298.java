package LeetCodes;
/*You have n boxes labeled from 0 to n - 1. You are given four arrays: status, candies, keys, and containedBoxes where:
status[i] is 1 if the ith box is open and 0 if the ith box is closed,
candies[i] is the number of candies in the ith box,
keys[i] is a list of the labels of the boxes you can open after opening the ith box.
containedBoxes[i] is a list of the boxes you found inside the ith box.
You are given an integer array initialBoxes that contains the labels of the boxes you initially have. You can take all the candies in any open box and you can use the keys in it to open new boxes and you also can use the boxes you find in it.
Return the maximum number of candies you can get following the rules above.
Example 1:
Input: status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
Output: 16
Explanation: You will be initially given box 0. You will find 7 candies in it and boxes 1 and 2.
Box 1 is closed and you do not have a key for it so you will open box 2. You will find 4 candies and a key to box 1 in box 2.
In box 1, you will find 5 candies and box 3 but you will not find a key to box 3 so box 3 will remain closed.
Total number of candies collected = 7 + 4 + 5 = 16 candy.
Example 2:
Input: status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
Output: 6
Explanation: You have initially box 0. Opening it you can find boxes 1,2,3,4 and 5 and their keys.
The total number of candies will be 6.*/
import java.util.*;

public class Q1298 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of boxes
        System.out.print("Enter number of boxes (n): ");
        int n = sc.nextInt();

        int[] status = new int[n];
        int[] candies = new int[n];
        int[][] keys = new int[n][];
        int[][] containedBoxes = new int[n][];

        // Input status array
        System.out.println("Enter status of each box (0 for closed, 1 for open):");
        for (int i = 0; i < n; i++) {
            status[i] = sc.nextInt();
        }

        // Input candies array
        System.out.println("Enter number of candies in each box:");
        for (int i = 0; i < n; i++) {
            candies[i] = sc.nextInt();
        }

        // Input keys array
        for (int i = 0; i < n; i++) {
            System.out.print("Enter number of keys in box " + i + ": ");
            int size = sc.nextInt();
            keys[i] = new int[size];
            if (size > 0) {
                System.out.println("Enter the keys in box " + i + ":");
                for (int j = 0; j < size; j++) {
                    keys[i][j] = sc.nextInt();
                }
            }
        }

        // Input containedBoxes array
        for (int i = 0; i < n; i++) {
            System.out.print("Enter number of boxes contained in box " + i + ": ");
            int size = sc.nextInt();
            containedBoxes[i] = new int[size];
            if (size > 0) {
                System.out.println("Enter the box numbers contained in box " + i + ":");
                for (int j = 0; j < size; j++) {
                    containedBoxes[i][j] = sc.nextInt();
                }
            }
        }

        // Input initialBoxes array
        System.out.print("Enter number of initial boxes you have: ");
        int m = sc.nextInt();
        int[] initialBoxes = new int[m];
        System.out.println("Enter the initial box numbers:");
        for (int i = 0; i < m; i++) {
            initialBoxes[i] = sc.nextInt();
        }
        Q1298 solution = new Q1298();
        int result = solution.maxCandies(status, candies, keys, containedBoxes, initialBoxes);
        System.out.println("Maximum number of candies you can collect: " + result);
    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] hasBox = new boolean[n];
        boolean[] hasKey = new boolean[n];
        boolean[] opened = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        for (int box : initialBoxes) {
            hasBox[box] = true;
            if (status[box] == 1) {
                queue.offer(box);
            }
        }

        int totalCandies = 0;

        while (!queue.isEmpty()) {
            int box = queue.poll();
            if (opened[box]) continue;
            opened[box] = true;
            totalCandies += candies[box];

            for (int key : keys[box]) {
                hasKey[key] = true;
                if (hasBox[key] && !opened[key]) {
                    queue.offer(key);
                }
            }

            for (int contained : containedBoxes[box]) {
                hasBox[contained] = true;
                if (status[contained] == 1 || hasKey[contained]) {
                    queue.offer(contained);
                }
            }
        }

        return totalCandies;
    }
}
