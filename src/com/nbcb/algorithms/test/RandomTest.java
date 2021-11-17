package com.nbcb.algorithms.test;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        int N = 1000;
        for (int i = 0; i < N; i++) {
            System.out.println(StdRandom.uniform());
        }
        System.out.println(timer.elapsedTime());
    }
}
