package LeetCodes;
/*Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
You must write an algorithm that runs in O(n) time and uses O(1) extra space. 
Example 1:
Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
Example 2:
Input: n = 2
Output: [1,2]*/
import java.util.*;

public class Q386 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value of n: ");
        int n = sc.nextInt();

        Q386 obj = new Q386();
        List<Integer> result = obj.lexicalOrder(n);

        System.out.println("Lexicographical order:");
        for (int num : result) {
            System.out.print(num + " ");
        }
        sc.close();
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>(n);
        int curr = 1;

        for (int i = 0; i < n; i++) {
            result.add(curr);
            if (curr * 10 <= n) {
                curr *= 10; // Go deeper in lexicographical tree
            } else {
                if (curr >= n) {
                    curr /= 10;
                }
                curr++;
                while (curr % 10 == 0) {
                    curr /= 10; // Go up to find the next valid sibling
                }
            }
        }
        return result;
    }
}

