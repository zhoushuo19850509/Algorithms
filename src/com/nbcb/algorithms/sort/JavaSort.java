package com.nbcb.algorithms.sort;

/**
 * 调用java自带的Arrays.sort()方法，看看性能如何
 *
 * 我们来看一下实际运行效果：
 * t1: 1.5639999999999983
 * t2: 1.7179999999999984
 * MergeSort is 1.1 times faster than JavaSort
 * 从实际运行效果来看，MergeSort的效率比JavaSort稍微快一点点(10%)
 * (2s内完成100次100000个节点的排序)
 *
 * 通过查询资料，Array.sort()的实现其实也是MergeSort，
 * 但是针对某个场景，比如排好反序，做了优化，新的算法叫做TimSort
 */
import java.util.Arrays;

public class JavaSort extends SortBase{

    @Override
    public void sort(Comparable[] a) {
        Arrays.sort(a);
    }


    // 用来调试MergeSort
    public static void main(String[] args) {
        String[] a = "S O R T E X A M P L E".split(" ");
        System.out.println("before sort");
        show(a);

        JavaSort javaSort = new JavaSort();
        javaSort.sort(a);

        System.out.println(isSort(a));

        System.out.println("after sort");
        System.out.println("is sort: " + isSort(a));
        show(a);
    }
}
