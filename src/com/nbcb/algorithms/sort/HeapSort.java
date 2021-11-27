package com.nbcb.algorithms.sort;

/**
 * Algorithm 2.7
 * 所谓的HeapSort，其实就是利用Binary Heap这个ADT，实现排序
 *
 */
public class HeapSort extends SortBase{


    /**
     * HeapSort的排序方法
     * 实现原理主要分为两个步骤：
     * 1.把array创建为binary heap
     * 2.把root node和最后一个节点(N)对换，通过sink(1)把root node节点下沉到合适的位置
     * 3.然后把root node和(N-1)对换，通过sink(1)把root node节点放到合适的位置
     * 4.不断重复，直到整个array是排序完成(升序)
     * @param a
     */
    @Override
    public void sort(Comparable[] a) {

        // N 代表arry中需要排序的元素的个数
        // 在heapsort中，array中的元素分布在： a[1]-[N-1]
        // 因此实际需要排序的元素数量为N - 1
        int N = a.length - 1;

        // 把array创建为binary heap
        for (int i = N/2 ; i >= 1; i--) {
            sink(a,i,N);
        }

        while(N > 1){
            exch(a,1, N--);
            sink(a,1,N);
        }
    }

    /**
     * 之前MaxPQ.java中已经实现了sink()，这里再实现一遍
     * sink()方法的原理就是遵循binary heap的架构，把node i下潜到合适的位置
     * @param a
     * @param i 当前要sink的节点
     * @param N
     */
    private void sink(Comparable[] a, int i, int N){

        while( 2*i <= N){
            // node j是node i 子节点(目前是左节点)
            int j = i*2;

            // 如果左子节点比右子节点小，就把j定位到右子节点
            if(j < N && less(a[j], a[j+1])){
                j++;
            }

            // 如果当前节点大于等于子节点(中较大的那个)，node i就已经完成了sink()
            if(!less(a[i],a[j])){
                break;
            }

            // 如果当前节点小于子节点(中较大的那个)，那就把当前节点和子节点调换一下
            exch(a,i,j);
            i = j;  // 然后当前节点定位到子节点(中较大的那个)
        }

    }

    public static void main(String[] args) {
        String[] a = "0 S O R T E X A M P L E".split(" ");
        System.out.println("before sort");
        show(a);

        HeapSort heapSort = new HeapSort();
        heapSort.sort(a);

        System.out.println(isSort(a));

        System.out.println("after sort");
        System.out.println("is sort: " + isSort(a));
        show(a);
    }
}
