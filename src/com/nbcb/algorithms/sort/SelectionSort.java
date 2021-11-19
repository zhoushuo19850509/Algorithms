package com.nbcb.algorithms.sort;

/**
 * Algorithm 2.1
 * 这个算法应该是所有sort算法中最简单的
 * 好像又称为冒泡算法
 *
 * 就是从左往右遍历各个元素，遍历到某个元素的时候
 * 找到这个元素右边最小的元素，把当前遍历到的元素和这个最小的元素互换
 *
 */
public class SelectionSort extends SortBase{

    /**
     * sort算法实现
     * @param a
     */
    public void sort(Comparable[] a){
        int N = a.length;
        // 从左往右遍历各个元素
        for (int i = 0; i < N; i++) {
            // a[min]代表从a[i]开始到最右边，最小的元素
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            /**
             * 遍历a[i]到a[N-1]各个元素完后，a[min]就是a[i]右侧最小的元素
             * 把a[i]和a[min]互换一下，互换之后，
             * 就能保证a[i]是a[i]到a[N-1]之间最小的的元素了
             */
            exch(a, i, min);
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

        (new SelectionSort()).sort(a);

        System.out.println(isSort(a));

        System.out.println("after sort");
        System.out.println("is sort: " + isSort(a));
        show(a);

    }
}
