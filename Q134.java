package LeetCodes;
/*There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.
Example 1:
Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:
Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start. */
import java.util.Scanner;
import java.util.InputMismatchException;

public class Q134 {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0, tank = 0, startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];

            if (tank < 0) {
                startIndex = i + 1;
                tank = 0;
            }
        }

        return (totalGas < totalCost) ? -1 : startIndex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter number of gas stations: ");
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Number of stations must be positive.");
                return;
            }

            int[] gas = new int[n];
            int[] cost = new int[n];

            System.out.println("Enter gas at each station:");
            for (int i = 0; i < n; i++) {
                gas[i] = scanner.nextInt();
            }

            System.out.println("Enter cost to travel from each station to next:");
            for (int i = 0; i < n; i++) {
                cost[i] = scanner.nextInt();
            }

            int result = canCompleteCircuit(gas, cost);
            if (result == -1) {
                System.out.println("Output: -1 (No valid starting station)");
            } else {
                System.out.println("Output: " + result + " (Start at station " + result + ")");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter only integers.");
        } finally {
            scanner.close();
        }
    }
}
