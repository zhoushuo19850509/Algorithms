package com.nbcb.algorithms.sort;

/**
 * Algorithm 2.4
 *
 * 所谓的MergeSort，其实就是把要排序的数组分成两块
 * 把两块分别排序后，在把两块合并起来
 *
 * 我们来看一下实际运行效果：
 * t1: 1.546999999999998
 * t2: 2.277
 * MergeSort is 1.5 times faster than ShellSort
 * 从实际运行效果来看，MergeSort的效率比ShellSort还要高50%
 * (2s内完成100次100000个节点的排序)
 *
 */
public class MergeSort extends SortBase{

    // 这个array是用来merge的
    private static Comparable[] aux;
    private static int mergeCount; // 第几次sort

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        mergeCount = 0;
        sort(a, 0, a.length - 1);
    }

    /**
     * 真正执行排序动作的方法
     * @param a
     * @param lo
     * @param hi
     */
    public static void sort(Comparable[] a , int lo, int hi){

        // 如果lo >= hi 说明已经排序完成 直接返回
        if(lo >= hi){
            return ;
        }

        int mid = lo + (hi - lo) / 2; // 统计array的mid index

        // (用于调试)排序前先打印一下两个array
//        System.out.printf("[%d]-[%d]-[%d] left array: %s right arry:%s\n" ,
//                 lo, mid, hi,
//                printArray(a,lo, mid), printArray(a, mid+1, hi));

        // 然后把array分成两块，分别排序
        sort(a, lo, mid);   // 对第一块进行排序
        sort(a, mid + 1, hi);  // 对第二块进行排序

        // (用于调试)排序后，合并前再打印一下两个array
//        System.out.printf("[%d]-[%d]-[%d] left array: %s right arry:%s\n" ,
//                 lo, mid, hi,
//                printArray(a,lo, mid), printArray(a, mid+1, hi));
        // 然后把分别排好序的两个array进行合并
        merge(a, lo, mid, hi);
    }


    /**
     * 这个方法是MergeSort排序算法中的核心方法
     * 用于将两块已经排好序的array进行合并
     * 合并的逻辑是这样的：
     * 1.先把a[]拷贝到一个新的array: aux[]
     * 2.aux[]中有两块排好序的array:
     *   aux[lo]-aux[mid]
     *   aux[mid + 1]-aux[hi]
     * 3.然后分别比对aux[]中这两块内容的元素，把结果放到a[]中
     * @param a
     * @param lo
     * @param mid
     * @param hi
     * @return
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi){

        int i = lo;     // i 代表第一块array的index
        int j = mid+1;  // j 代表第二块array的index

        // 把a[]拷贝到一个新的array: aux[]
        for (int t = lo; t <= hi; t++) {
            aux[t] = a[t];
        }

        // 然后分别比对aux[]中这两块内容的元素，把结果放到a[]中
        for(int t = lo; t <= hi ; t++){
            if(i > mid){
                // 说明第一个数组已经空了，直接把第二个数组的节点依次赋值给a[t]就行了
                a[t] = aux[j++];
            }else if(j > hi){
                // 说明第二个数组已经空了，直接把第一个数组的节点依次赋值给a[t]就行了
                a[t] = aux[i++];
            }else if(less(aux[j], aux[i])){
                // 两个数组中都还有数据，优先把小的数据(a[j])放到a[t]
                a[t] = aux[j++];
            }else{
                // a[j] >= a[i]，把a[i]放到a[t]
                a[t] = aux[i++];
            }
        }
    }



    // 用来调试MergeSort
    public static void main(String[] args) {
        String[] a = "S O R T E X A M P L E".split(" ");
        System.out.println("before sort");
        show(a);

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(a);

        System.out.println(isSort(a));

        System.out.println("after sort");
        System.out.println("is sort: " + isSort(a));
        show(a);
    }
}
