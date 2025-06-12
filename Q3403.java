package LeetCodes;
/*You are given a string word, and an integer numFriends.
Alice is organizing a game for her numFriends friends. There are multiple rounds in the game, where in each round:
word is split into numFriends non-empty strings, such that no previous round has had the exact same split.
All the split words are put into a box.
Find the lexicographically largest string from the box after all the rounds are finished.
Example 1:
Input: word = "dbca", numFriends = 2
Output: "dbc"
Explanation: 
All possible splits are:
"d" and "bca".
"db" and "ca".
"dbc" and "a".
Example 2:
Input: word = "gggg", numFriends = 4
Output: "g"
Explanation: 
The only possible split is: "g", "g", "g", and "g".*/
import java.util.*;

public class Q3403 {
    static String maxStr = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompting the user for input
        System.out.print("Enter the string word: ");
        String word = sc.next();

        System.out.print("Enter the number of friends (numFriends): ");
        int numFriends = sc.nextInt();
        solve(word, numFriends);

        // Output the result
        System.out.println("Lexicographically largest string from the box: " + maxStr);
    }

    static void solve(String word, int k) {
        List<String> current = new ArrayList<>();
        Set<String> seenSplits = new HashSet<>();
        backtrack(word, 0, k, current, seenSplits);
    }

    static void backtrack(String word, int index, int k, List<String> current, Set<String> seenSplits) {
        if (current.size() > k) return;

        if (index == word.length() && current.size() == k) {
            String key = String.join("|", current);
            if (!seenSplits.contains(key)) {
                seenSplits.add(key);
                for (String part : current) {
                    if (maxStr.compareTo(part) < 0) {
                        maxStr = part;
                    }
                }
            }
            return;
        }

        for (int i = index + 1; i <= word.length(); i++) {
            String part = word.substring(index, i);
            current.add(part);
            backtrack(word, i, k, current, seenSplits);
            current.remove(current.size() - 1);
        }
    }
}
