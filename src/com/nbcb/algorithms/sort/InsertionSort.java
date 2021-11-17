package com.nbcb.algorithms.sort;


/**
 * Algorithm 2.2
 * InsertionSort的原理和打牌中的理牌差不多，
 * 意思就是给某个节点找一个适当的位置
 */
public class InsertionSort {

    /**
     * sort算法实现
     * @param a
     */
    public static void sort(Comparable[] a){
        int N = a.length;
        // 从左往右遍历各个元素
        for (int i = 1; i < N; i++) {
            // 这个循环的意思就是，给a[i]找一个适当的位置，然后insert，有点像理牌
            for(int j = i; j > 0 && less(a[j],a[j - 1]) ; j--){
                exch(a, j -1, j);
            }
        }
    }

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    /**
     * exchange element a[i] and a[j]
     * @param a
     * @param j
     * @param j
     */
    public static void exch(Comparable[] a ,int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * show the sorted array
     * @param a
     */
    public static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * verify the array has been sorted or not
     * @param a
     * @return
     */
    public static boolean isSort(Comparable[] a){
        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i - 1])){
                return false;
            }
        }
        return true;
    }

    /**
     * main() 方法用于验证sort算法是否正常
     * @param args
     */
    public static void main(String[] args) {

        String[] a = "S O R T E X A M P L E".split(" ");
        System.out.println("before sort");
        show(a);

        sort(a);

        System.out.println(isSort(a));

        System.out.println("after sort");
        show(a);

    }
}
