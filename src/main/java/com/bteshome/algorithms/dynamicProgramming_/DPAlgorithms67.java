package com.bteshome.algorithms.dynamicProgramming_;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DPAlgorithms67 {
    /**
     * https://www.hackerrank.com/challenges/sam-and-substrings/problem
     * */
    public static class SamAndSubstrings {
        private static final long MOD = 1000000007;

        public static int substrings(String n) {
            if (n.isEmpty())
                return 0;

            long dp = 0;
            long totalSum = 0;

            for (int i = 0; i < n.length(); i++) {
                int digit = n.charAt(i) - '0';
                dp = (((dp * 10L) % MOD) + ((i + 1L) * digit)) % MOD;
                totalSum = (totalSum + dp) % MOD;
            }

            return (int) totalSum;
        }
    }

    /**
     * https://www.hackerrank.com/challenges/prime-xor/problem
     * TODO
     * NOTE: even with precomputation of primes, this solution doesn't pass time limit test
     * */
    public static class PrimeXor {
        private static final long MOD = 1000000007;

        public static int primeXor(List<Integer> a) {
            if (a == null || a.isEmpty())
                return 0;

            //Boolean[] primes = new Boolean[1 << 13];
            boolean[] primes = sieveOfEratosthenes(1 << 13);
            Collections.sort(a);

            return primeXor(a, 0, 0, primes);
        }

        private static int primeXor(List<Integer> a, int pos, int xor, boolean[] primes) {
            long count = 0;

            if (primes[xor])
                count++;

            for (int i = pos; i < a.size(); i++) {
                if (i > pos && a.get(i).equals(a.get(i - 1)))
                    continue;
                count = (count + primeXor(a, i + 1, xor ^ a.get(i), primes)) % MOD;
            }

            return (int)count;
        }

        private static boolean[] sieveOfEratosthenes(int n) {
            boolean[] isPrime = new boolean[n];
            Arrays.fill(isPrime, true);
            isPrime[0] = isPrime[1] = false;

            for (int i = 2; i * i < n; i++)
                if (isPrime[i])
                    for (int j = i * i; j < n; j += i)
                        isPrime[j] = false;

            return isPrime;
        }

        private static boolean isPrime(int n, Boolean[] primes) {
            if (primes[n] != null)
                return primes[n];

            boolean is = true;

            if (n <= 1)
                is = false;
            else if (n <= 3)
                is = true;
            else if (n % 2 == 0 || n % 3 == 0)
                is = false;
            else {
                for (int i = 5; (i * i) <= n; i = i + 6) {
                    if (n % i == 0 || n % (i + 2) == 0) {
                        is = false;
                        break;
                    }
                }
            }

            primes[n] = is;

            return is;
        }
    }
}