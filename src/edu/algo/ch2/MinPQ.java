package edu.algo.ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;
    private int max;

    public MinPQ(int maxCapacity) {
        if (maxCapacity <= 0) throw new IndexOutOfBoundsException();
        max = maxCapacity;
        pq = (Key[]) new Comparable[max + 1];
        N = 0;
    }

    public void add(Key key) {
        if (N >= max) throw new ArrayIndexOutOfBoundsException();
        pq[++N] = key;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1) {
            if (!less(k, k / 2)) break;
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }


    public Key delMin() {
        Key t = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return t;
    }

    private void sink(int k) {
        int j;
        while ((j = k*2) < N) {
            if (j < N && less(j+1, j)) j = j+1;
            if (!less(j, k)) break;
            exch(j, k);
            k = j;
        }
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public void print() {
        for (int i = 0; i <= N; i++) {
            System.out.print(pq[i] + " => ");
        }
        System.out.println("done");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("data/ch2/tinyPQ.txt"));
        String[] t = br.readLine().trim().split("[ -]+");
        br.close();
        MinPQ<String> pq = new MinPQ<>(t.length);
        for(String s: t) {
            System.out.println("Inserting " + s);
            pq.add(s);
        }
        pq.print();
        for (int i = 0; i < t.length; i++) {
            System.out.println("Deleting: " + pq.delMin());
        }
    }
}
