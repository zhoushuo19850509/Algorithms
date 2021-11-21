package com.nbcb.algorithms.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Algorithm 2.5
 * 所谓的快排，思路和MergeSort差不多，就是采用分而治之的策略
 * 实现思路是这样的：
 * 1.先找一个Node，比如array[0]
 * 2.然后遍历这个array，把所有小于等于Node的节点放到left array
 *   把所有大于Node的节点放到right array
 * 3.然后分别对left array和right array进行排序，排序策略同上
 * 4.等left array和right array都排好序之后，整个array就已经排完序了
 */
public class QuickSort extends SortBase{

    /**
     * QuickSort算法的实现逻辑
     * @param a
     */
    @Override
    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public void sort(Comparable[] a, int lo, int hi){

        if(lo >= hi){
            return;
        }
        int p = partition(a, lo, hi);
        sort(a, lo, p - 1);
        sort(a, p + 1 , hi);

    }


    /**
     * partition这个方法的作用是这样的
     * 1.先找一个partition Node，比如array[0]
     * 2.把array分为两部分，left array和right array
     *   其中left array中所有节点都小于等于Node
     *   right array中所有节点都大于Node
     * @param a 等待partition的array
     * @param lo start index
     * @param hi end index
     * @return  partition结束后，partition Node的位置
     */
    public static int partition(Comparable[] a , int lo, int hi){
        Comparable p = a[lo];

        // index i scan from left to right
        int i = lo;
        // index j scan from right to left
        int j = hi + 1;

        // outer loop
        while(true){
            /**
             * inner loop1
             * 从左到右扫描，直到扫描到大于等于partition node的元素
             * 当然，如果扫描到最右也立刻退出
             */
            while(less(a[++i],p)){
                if(i == hi){
                    break;
                }
            }
            /**
             * inner loop2
             * 从右到左扫描，直到扫描到小于等于partition node的元素
             * 当然，如果扫描到最左，也立刻退出
             */
            while(less(p,a[--j])){
                if(j == lo){
                    break;
                }
            }
            // 如果i >= j 说明相向的两个scan，碰到了
            if(i >= j){
                break;
            }
            /**
             * 在这里交换是什么意思呢？
             * 参考两个inner loop，两个loop都退出的意思是
             * loop1碰到了大于等于partition node的元素
             * loop2碰到了小于等于partition node的元素
             *
             * 在这里经过交换后，就能够继续扫描了
             */
            exch(a, i, j);
        }
        /**
         * 当out loop结束后，整个partition就结束了
         * 最后这个exch()的意思是，把partition node放到真正的中间位置
         * 这个位置左边所有元素都小于等于partition node，右边的元素都大于等于partition node
         * 这里的j要好好回味一下，这个节点是left array最右边的index
         */
        exch(a, lo, j);
        return j;

    }

    public static void main(String[] args) {
        String[] a = "S O R T E X A M P L E".split(" ");
        System.out.println("before sort");
        show(a);

        QuickSort quickSort = new QuickSort();
        quickSort.sort(a);

        System.out.println(isSort(a));

        System.out.println("after sort");
        System.out.println("is sort: " + isSort(a));
        show(a);
    }


}
