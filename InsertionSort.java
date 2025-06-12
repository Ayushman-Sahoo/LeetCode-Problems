package LeetCodes;

import java.util.Scanner;

public class InsertionSort {

    // Insertion Sort function
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Place the key at the correct position
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask for number of elements
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        // Handle empty array case
        if (n <= 0) {
            System.out.println("Sorted array: ");
            sc.close();
            return;
        }

        int[] arr = new int[n];

        // Ask user to enter the elements
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Sort the array
        insertionSort(arr);

        // Print the sorted array
        System.out.print("Sorted array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
