package com.nbcb.algorithms.sort;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 这个类的功能正如它的名字，是用来比较两个sort算法，
 * 比较哪个性能更好
 */
public class SortCompare {

    /**
     * 通过算法alg，对数组a[]排序一次，耗时多久
     * @param alg
     * @param a
     * @return
     */
    public static double time(String alg,Comparable[] a){
        Stopwatch timer = new Stopwatch();

        if(alg.equals("SelectionSort")){
            (new SelectionSort()).sort(a);
        }
        if(alg.equals("InsertionSort")){
            (new InsertionSort()).sort(a);
        }
        if(alg.equals("ShellSort")){
            (new ShellSort()).sort(a);
        }
        if(alg.equals("MergeSort")){
            (new MergeSort()).sort(a);
        }

        // 检查一下排序结果是否正确
        if(!InsertionSort.isSort(a)){
            System.out.println("not sorted!");
        }

        return timer.elapsedTime();
    }


    /**
     * 通过某个算法实现sort，耗时多久
     * @param alg sort算法
     * @param N N代表需要sort的数组有多少元素
     * @param T T代表整个sort的动作要重复多少次
     * @return
     */
    public static double timeRandomInput(String alg, int N ,int T){
        // 耗时总计
        double total = 0.0;
        Comparable[] a = new Comparable[N];

        // 先重复T次
        for (int t = 0; t < T; t++) {
            // 先创建一组随机数，组成数组，用于sort
            for (int n = 0; n < N; n++) {
                a[n] = StdRandom.uniform();
            }
            // 调用alg算法，对a[]进行排序
            total += time(alg, a);
        }
        return total;
    }


    public static void main(String[] args) {

        // 先指定两种sort算法
        String alg1 = "MergeSort";
        String alg2 = "ShellSort";

        // N代表需要sort的数组有多少元素
        int N = 100000;

        // T代表整个sort的动作要重复多少次
        int T = 100;

        double t1 = timeRandomInput(alg1, N ,T);
        double t2 = timeRandomInput(alg2, N ,T);

        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);

        System.out.printf("%s is %.1f times faster than %s",alg1, (t2/t1) ,alg2);

    }
}
