package com.nbcb.algorithms.test;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        int N = 100;
        for (int i = 0; i < N; i++) {
            System.out.println(StdRandom.random());
        }
    }
}
