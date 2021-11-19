package com.nbcb.algorithms.sort;


/**
 * Algorithm 2.2
 * InsertionSort的原理和打牌中的理牌差不多，
 * 意思就是给某个节点找一个适当的位置
 */
public class InsertionSort extends SortBase{

    /**
     * sort算法实现
     * @param a
     */
    public void sort(Comparable[] a){
        int N = a.length;
        // 从左往右遍历各个元素
        for (int i = 1; i < N; i++) {
            // 这个循环的意思就是，给a[i]找一个适当的位置，然后insert，有点像理牌
            for(int j = i; j > 0 && less(a[j],a[j - 1]) ; j--){
                exch(a, j -1, j);
            }
        }
    }

    /**
     * main() 方法用于验证sort算法是否正常
     * @param args
     */
    public static void main(String[] args) {

        String[] a = "S O R T E X A M P L E".split(" ");
        System.out.println("before sort");
        show(a);

        (new InsertionSort()).sort(a);

        System.out.println(isSort(a));

        System.out.println("after sort");
        System.out.println("is sort: " + isSort(a));
        show(a);

    }
}
