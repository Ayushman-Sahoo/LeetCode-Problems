package LeetCodes;
/*You are given a 0-indexed array of strings words and a character x.
Return an array of indices representing the words that contain the character x.
Note that the returned array may be in any order.
Example 1:
Input: words = ["leet","code"], x = "e"
Output: [0,1]
Explanation: "e" occurs in both words: "leet", and "code". Hence, we return indices 0 and 1.
Example 2:
Input: words = ["abc","bcd","aaaa","cbc"], x = "a"
Output: [0,2]
Explanation: "a" occurs in "abc", and "aaaa". Hence, we return indices 0 and 2.
Example 3:
Input: words = ["abc","bcd","aaaa","cbc"], x = "z"
Output: []
Explanation: "z" does not occur in any of the words. Hence, we return an empty array.*/
import java.util.*;

public class Q2942 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of words
        System.out.print("Enter the number of words: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Input words
        String[] words = new String[n];
        System.out.println("Enter the words:");
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
        }

        // Input character x
        System.out.print("Enter the character to search for: ");
        char x = scanner.next().charAt(0);

        // Call the method and get result
        List<Integer> result = findWordsContaining(words, x);

        // Output result
        System.out.println("Indices of words containing '" + x + "': " + result);
    }

    public static List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) != -1) {
                result.add(i);
            }
        }

        return result;
    }
}

