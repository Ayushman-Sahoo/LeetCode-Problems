package LeetCodes;
/*You are given a positive integer n, representing an n x n city. You are also given a 2D grid buildings, where buildings[i] = [x, y] denotes a unique building located at coordinates [x, y].
A building is covered if there is at least one building in all four directions: left, right, above, and below.
Return the number of covered buildings.
Input: n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]
Output: 1
Explanation:
Only building [2,2] is covered as it has at least one building:
above ([1,2])
below ([3,2])
left ([2,1])
right ([2,3])
Thus, the count of covered buildings is 1. 
Input: n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]
Output: 0
Explanation:
No building has at least one building in all four directions.
Input: n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]
Output: 1
Explanation:
Only building [3,3] is covered as it has at least one building:
above ([1,3])
below ([5,3])
left ([3,2])
right ([3,5])
Thus, the count of covered buildings is 1.*/
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class CountCovered {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the size of the city (n): ");
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("City size must be a positive integer.");
                return;
            }

            System.out.print("Enter the number of buildings: ");
            int numBuildings = scanner.nextInt();
            if (numBuildings < 0) {
                System.out.println("Number of buildings cannot be negative.");
                return;
            }

            int[][] buildings = new int[numBuildings][2];
            System.out.println("Enter the coordinates of each building (x y), each on a new line:");
            for (int i = 0; i < numBuildings; i++) {
                try {
                    buildings[i][0] = scanner.nextInt();
                    buildings[i][1] = scanner.nextInt();
                    if (buildings[i][0] < 1 || buildings[i][0] > n || buildings[i][1] < 1 || buildings[i][1] > n) {
                        System.out.println("Building coordinates must be within the city bounds (1 to " + n + "). Please re-enter.");
                        i--; // Decrement to re-enter the current building's coordinates
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter two integers for the coordinates.");
                    scanner.next(); // Consume the invalid input
                    i--; // Decrement to re-enter the current building's coordinates
                }
            }

            CountCovered solution = new CountCovered();
            int coveredCount = solution.countCoveredBuildings(n, buildings);
            System.out.println("Number of covered buildings: " + coveredCount);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input for city size or number of buildings. Please enter integers.");
        } finally {
            scanner.close();
        }
    }

    public int countCoveredBuildings(int n, int[][] buildings) {
        Set<String> buildingSet = new HashSet<>();
        Map<Integer, Set<Integer>> rows = new HashMap<>();
        Map<Integer, Set<Integer>> cols = new HashMap<>();

        for (int[] building : buildings) {
            int r = building[0];
            int c = building[1];
            buildingSet.add(r + "," + c);
            rows.computeIfAbsent(r, k -> new HashSet<>()).add(c);
            cols.computeIfAbsent(c, k -> new HashSet<>()).add(r);
        }

        int coveredCount = 0;
        for (int[] building : buildings) {
            int r = building[0];
            int c = building[1];

            boolean hasAbove = false;
            for (int i = r - 1; i >= 1; i--) {
                if (buildingSet.contains(i + "," + c)) {
                    hasAbove = true;
                    break;
                }
            }

            boolean hasBelow = false;
            for (int i = r + 1; i <= n; i++) {
                if (buildingSet.contains(i + "," + c)) {
                    hasBelow = true;
                    break;
                }
            }

            boolean hasLeft = false;
            for (int j = c - 1; j >= 1; j--) {
                if (buildingSet.contains(r + "," + j)) {
                    hasLeft = true;
                    break;
                }
            }

            boolean hasRight = false;
            for (int j = c + 1; j <= n; j++) {
                if (buildingSet.contains(r + "," + j)) {
                    hasRight = true;
                    break;
                }
            }

            if (hasAbove && hasBelow && hasLeft && hasRight) {
                coveredCount++;
            }
        }
        return coveredCount;
    }
}