package LeetCodes;
/*You are given three integers n, m, k. A good array arr of size n is defined as follows:
Each element in arr is in the inclusive range [1, m].
Exactly k indices i (where 1 <= i < n) satisfy the condition arr[i - 1] == arr[i].
Return the number of good arrays that can be formed.
Since the answer may be very large, return it modulo 109 + 7.
Example 1:
Input: n = 3, m = 2, k = 1
Output: 4
Explanation:
There are 4 good arrays. They are [1, 1, 2], [1, 2, 2], [2, 1, 1] and [2, 2, 1].
Hence, the answer is 4.
Example 2:
Input: n = 4, m = 2, k = 2
Output: 6
Explanation:
The good arrays are [1, 1, 1, 2], [1, 1, 2, 2], [1, 2, 2, 2], [2, 1, 1, 1], [2, 2, 1, 1] and [2, 2, 2, 1].
Hence, the answer is 6.
Example 3:
Input: n = 5, m = 2, k = 0
Output: 2
Explanation:
The good arrays are [1, 2, 1, 2, 1] and [2, 1, 2, 1, 2]. Hence, the answer is 2.*/
import java.util.*;

public class Q3405 {
    static final int MOD = 1_000_000_007;
    static final int MAX = 100_005;
    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        
        System.out.print("Enter m: ");
        int m = sc.nextInt();
        
        System.out.print("Enter k: ");
        int k = sc.nextInt();
        
        Q3405 obj = new Q3405();
        int result = obj.countGoodArrays(n, m, k);
        System.out.println("Number of good arrays: " + result);
        
        sc.close();
    }

    public int countGoodArrays(int n, int m, int k) {
        precomputeFactorials(n);
        long comb = nCr(n - 1, k);
        long power = modPow(m - 1, n - k - 1);
        long result = comb * m % MOD * power % MOD;
        return (int) result;
    }
    // Precompute factorials and inverse factorials
    void precomputeFactorials(int N) {
        fact[0] = invFact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[N] = modInverse(fact[N]);
        for (int i = N - 1; i >= 1; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
    }
    long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }
    long modPow(long base, long exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }
    long modInverse(long x) {
        return modPow(x, MOD - 2);
    }
}
