package LeetCodes;
/*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself. 
Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]*/
import java.util.Scanner;

public class Q2 {

    // Definition for singly-linked list
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Method to add two numbers represented by linked lists
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;

            current.next = new ListNode(sum % 10);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }

    // Helper to create a linked list from an array
    public static ListNode createLinkedList(int[] digits) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int digit : digits) {
            curr.next = new ListNode(digit);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper to print linked list
    public static void printLinkedList(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) System.out.print(" -> ");
            node = node.next;
        }
        System.out.println();
    }

    // Main method to read input and invoke the logic
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter digits of first number in reverse order (comma separated, e.g., 2,4,3):");
            String[] input1 = scanner.nextLine().split(",");
            int[] num1 = new int[input1.length];
            for (int i = 0; i < input1.length; i++) {
                num1[i] = Integer.parseInt(input1[i].trim());
            }

            System.out.println("Enter digits of second number in reverse order (comma separated, e.g., 5,6,4):");
            String[] input2 = scanner.nextLine().split(",");
            int[] num2 = new int[input2.length];
            for (int i = 0; i < input2.length; i++) {
                num2[i] = Integer.parseInt(input2[i].trim());
            }

            ListNode l1 = createLinkedList(num1);
            ListNode l2 = createLinkedList(num2);

            Q2 solution = new Q2();
            ListNode result = solution.addTwoNumbers(l1, l2);

            System.out.print("Output: ");
            printLinkedList(result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter only integers separated by commas.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

