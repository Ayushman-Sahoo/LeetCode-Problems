package LeetCodes;

import java.util.InputMismatchException;
import java.util.Scanner;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BST {
    Node root;

    BST() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null) return new Node(key);

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    boolean search(int key) {
        return searchRec(root, key) != null;
    }

    Node searchRec(Node root, int key) {
        if (root == null || root.key == key) return root;
        if (key < root.key)
            return searchRec(root.left, key);
        return searchRec(root.right, key);
    }

    void delete(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null) return null;

        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root) {
        int min = root.key;
        while (root.left != null) {
            root = root.left;
            min = root.key;
        }
        return min;
    }

    void inorder() {
        System.out.print("Inorder: ");
        inorderRec(root);
        System.out.println();
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    void preorder() {
        System.out.print("Preorder: ");
        preorderRec(root);
        System.out.println();
    }

    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    void postorder() {
        System.out.print("Postorder: ");
        postorderRec(root);
        System.out.println();
    }

    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }
}

public class BSTTraversal {
    public static void main(String[] args) {
        BST tree = new BST();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            try {
                System.out.println("\n--- Binary Search Tree Menu ---");
                System.out.println("1. Insert (multiple values space-separated)");
                System.out.println("2. Delete");
                System.out.println("3. Search");
                System.out.println("4. Inorder Traversal");
                System.out.println("5. Preorder Traversal");
                System.out.println("6. Postorder Traversal");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                // Try to read integer
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter space-separated values to insert: ");
                        String line = scanner.nextLine().trim();
                        if (line.isEmpty()) {
                            System.out.println("No input provided.");
                            break;
                        }
                        String[] values = line.split("\\s+");
                        for (String val : values) {
                            try {
                                int num = Integer.parseInt(val);
                                tree.insert(num);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number skipped: " + val);
                            }
                        }
                        break;

                    case 2:
                        System.out.print("Enter value to delete: ");
                        try {
                            int del = Integer.parseInt(scanner.nextLine());
                            tree.delete(del);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter value to search: ");
                        try {
                            int search = Integer.parseInt(scanner.nextLine());
                            System.out.println(tree.search(search) ? "Found!" : "Not found.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                        break;

                    case 4:
                        tree.inorder();
                        break;

                    case 5:
                        tree.preorder();
                        break;

                    case 6:
                        tree.postorder();
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number from 0 to 6.");
                }

            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
                // Skip faulty input and continue
            }

        } while (choice != 0);
    }
}
