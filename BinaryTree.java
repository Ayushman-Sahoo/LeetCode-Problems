package LeetCodes;
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int value) {
        data = value;
        left = right = null;
    }
}
public class BinaryTree {
static Scanner sc = new Scanner(System.in);
    Node root;

    // Insert a node into the binary search tree
    Node insert(Node root, int data) {
        if (root == null)
            return new Node(data);
        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);
        return root;
    }
// Inorder Traversal (Left, Root, Right)
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }
    // Preorder Traversal (Root, Left, Right)
    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }
    // Postorder Traversal (Left, Right, Root)
    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }
// Level-order (Breadth-first)
    void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node temp = q.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null) q.add(temp.left);
            if (temp.right != null) q.add(temp.right);
        }
    }
    // Search a node
    boolean search(Node root, int key) {
        if (root == null)
            return false;
        if (key == root.data)
            return true;
        else if (key < root.data)
            return search(root.left, key);
        else
            return search(root.right, key);
    }
    // Find min value in tree
    Node findMin(Node root) {
        while (root.left != null)
            root = root.left;
        return root;
    }
    // Delete a node
    Node delete(Node root, int key) {
        if (root == null) return null;
        if (key < root.data)
            root.left = delete(root.left, key);
        else if (key > root.data)
            root.right = delete(root.right, key);
        else {
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            // Node with two children
            Node minNode = findMin(root.right);
            root.data = minNode.data;
            root.right = delete(root.right, minNode.data);
        }
        return root;
    }
    // Calculate height/depth of the tree
    int height(Node root) {
        if (root == null)
            return -1;
        return 1 + Math.max(height(root.left), height(root.right));
    }
    // Menu-driven program
    void menu() {
        int choice;
        do {
            System.out.println("\n--- Binary Tree Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Inorder Traversal");
            System.out.println("3. Preorder Traversal");
            System.out.println("4. Postorder Traversal");
            System.out.println("5. Level-order Traversal");
            System.out.println("6. Search");
            System.out.println("7. Delete");
            System.out.println("8. Height of Tree");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            int value;
            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = sc.nextInt();
                    root = insert(root, value);
                    break;
                case 2:
                    System.out.print("Inorder: ");
                    inorder(root);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Preorder: ");
                    preorder(root);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Postorder: ");
                    postorder(root);
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Level-order: ");
                    levelOrder(root);
                    System.out.println();
                    break;
                case 6:
                    System.out.print("Enter value to search: ");
                    value = sc.nextInt();
                    System.out.println(search(root, value) ? "Found" : "Not Found");
                    break;
                case 7:
                    System.out.print("Enter value to delete: ");
                    value = sc.nextInt();
                    root = delete(root, value);
                    break;
                case 8:
                    System.out.println("Height of tree: " + height(root));
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 9);
    }
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.menu();
}
}
