package edu.algo.ch1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BinarySearch {
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/ch1/largeT.txt"));
        String line;
        ArrayList<Integer> input = new ArrayList<>();
        long start = System.currentTimeMillis();
        while ((line = br.readLine()) != null) {
            input.add(Integer.parseInt(line.trim()));
        }
        br.close();
        System.out.println("Read time: " + ((System.currentTimeMillis() - start)/1000) + "s");
        int[] whitelist = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            whitelist[i] = input.get(i);
        }

        int[] keys = {499569, 984875};
        for (int key : keys) {
            if (rank(key, whitelist) == -1) {
                System.out.println(key);
            }
        }
        System.out.println("Completion time: " + ((System.currentTimeMillis() - start)/1000) + "s");
    }
}
