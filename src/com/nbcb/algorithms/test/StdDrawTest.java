package com.nbcb.algorithms.test;


import edu.princeton.cs.algs4.StdDraw;

public class StdDrawTest {
    public static void main(String[] args) {
        int N = 100;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N * N);

        for (int i = 0; i < N; i++) {
            StdDraw.point(i, i );
            StdDraw.point(i, i * i);

        }
    }
}
