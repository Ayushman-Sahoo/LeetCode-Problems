package LeetCodes;

import java.util.*;

public class BinarySearchTree {
    static class Node {
        int key;
        Node left, right;

        Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    // Insert a new key
    Node insert(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insert(root.left, key);
        else if (key > root.key)
            root.right = insert(root.right, key);
        return root;
    }

    // Inorder traversal (sorted order)
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    // Search for a key
    boolean search(Node root, int key) {
        if (root == null)
            return false;
        if (root.key == key)
            return true;
        return key < root.key ? search(root.left, key) : search(root.right, key);
    }

    // Delete a key
    Node delete(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node with two children
            root.key = minValue(root.right);
            root.right = delete(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            tree.root = tree.insert(tree.root, val);
        }

        System.out.print("Inorder traversal: ");
        tree.inorder(tree.root);
        System.out.println();

        // Search test
        System.out.print("Enter key to search: ");
        int searchKey = sc.nextInt();
        System.out.println("Found: " + tree.search(tree.root, searchKey));

        // Delete test
        System.out.print("Enter key to delete: ");
        int deleteKey = sc.nextInt();
        tree.root = tree.delete(tree.root, deleteKey);

        System.out.print("Inorder after deletion: ");
        tree.inorder(tree.root);
        System.out.println();
    }
}
