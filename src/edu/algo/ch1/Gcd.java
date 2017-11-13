package edu.algo.ch1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 Gcd(p, q): If q == 0, p is the Gcd, else r = p % q; Gcd(q, r);
 */
public class Gcd {
    public static int gcd(int p, int q) {
        if (q == 0)  return p;
        int r = p % q;
        return gcd(q, r);
    }
}
