package LeetCodes;
/*You are given two strings of the same length s1 and s2 and a string baseStr.
We say s1[i] and s2[i] are equivalent characters.
For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:
Reflexivity: 'a' == 'a'.
Symmetry: 'a' == 'b' implies 'b' == 'a'.
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.
Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
Example 1:
Input: s1 = "parker", s2 = "morris", baseStr = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
The characters in each group are equivalent and sorted in lexicographical order.
So the answer is "makkek".
Example 2:
Input: s1 = "hello", s2 = "world", baseStr = "hold"
Output: "hdld"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".
Example 3:
Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
Output: "aauaaaaada"
Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".*/
import java.util.*;

public class Q1061 {
    static int[] parent = new int[26];

    // Find function with path compression
    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union function to merge sets by lexicographical order
    private static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        if (px < py) {
            parent[py] = px;
        } else {
            parent[px] = py;
        }
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        // Initialize each character as its own parent
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // Build equivalence groups
        for (int i = 0; i < s1.length(); i++) {
            union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        // Build the result using smallest equivalent characters
        StringBuilder sb = new StringBuilder();
        for (char ch : baseStr.toCharArray()) {
            int rep = find(ch - 'a');
            sb.append((char) (rep + 'a'));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string s1: ");
        String s1 = sc.nextLine();

        System.out.print("Enter string s2: ");
        String s2 = sc.nextLine();

        System.out.print("Enter baseStr: ");
        String baseStr = sc.nextLine();

        String result = smallestEquivalentString(s1, s2, baseStr);
        System.out.println("Smallest Equivalent String: " + result);
    }
}
