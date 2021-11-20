package com.nbcb.algorithms.sort;

/**
 * Algorithm 2.3
 * ShellSort是为了进一步提升InsertionSort的效率，提出的一个优化算法。
 * InsertionSort有什么问题呢？我们每次挑选一个Node，把它插到前面某个位置的时候
 * 需要和前面的Node，逐个比较(调用exch()方法)。这个效率有点低。
 *
 * 为了提升效率，shell sort的思路是这样的：
 * 先挑选一个间隔范围(gap)，比如gap = N/3
 * 然后在这个gap基础上，对间隔gap长度的两个node进行比较，
 * 保证那些保持gap距离的节点，都是排好序的
 * 然后逐步缩小gap范围，直到gap=1
 *
 * 我们来看一下实际运行效果：
 * t1: 2.2709999999999977
 * t2: 784.1589999999998
 * ShellSort is 345.3 times faster than InsertionSort
 * 在数据量较大的情况下，ShellSort效率还是非常高的(2s内完成100次100000个节点的排序)
 *
 */
public class ShellSort extends SortBase{

    /**
     * sort算法实现
     * @param a
     */
    public void sort(Comparable[] a){
        int N = a.length;
        int h = 1;   // 作为gap值
        // 先让h逼近N/3左右
        while(h < N/3){
            h = 3 * h + 1;
        }

        while(h >=1 ){
            for (int i = h; i < N; i++) {
                /**
                 * 内部这个循环的意思是，遍历i->N的各个节点，比如遍历到节点n
                 * 然后按照打牌的思路把a[n]节点插入到a[n] a[n-h] a[n-2h] ...中某个合适的位置
                 */
                for (int j = i; j >= h && less(a[j],a[j-h]) ; j -= h) {
                    exch(a, j, j-h);
                }
            }
            // 等到两层循环结束后，能够保证相距gap=N上的节点都是排好序的

            h = h / 3;  // 缩小gap范围，继续排序
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

        ShellSort shellSort = new ShellSort();
        shellSort.sort(a);

        System.out.println(isSort(a));

        System.out.println("after sort");
        System.out.println("is sort: " + isSort(a));
        show(a);

    }
}
