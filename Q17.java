package LeetCodes;
/*Given a string s consisting of lowercase English Letters. Return the first non-repeating character in s.
If there is no non-repeating character, return '$'.
Note: When you return '$' driver code will output -1.

Examples:
Input: s = "geeksforgeeks"
Output: 'f'
Explanation: In the given string, 'f' is the first character in the string which does not repeat.
Input: s = "racecar"
Output: 'e'
Explanation: In the given string, 'e' is the only character in the string which does not repeat.
Input: s = "aabbccc"
Output: -1
Explanation: All the characters in the given string are repeating.*/
import java.util.*;

public class Q17 {
    static char nonRepeatingChar(String s) {
        Map<Character, Integer> freqMap = new LinkedHashMap<>();
        
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return '$';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String input = scanner.nextLine();
        char result = nonRepeatingChar(input);
        
        if (result == '$') {
            System.out.println("-1");
        } else {
            System.out.println("First non-repeating character: " + result);
        }
    }
}
