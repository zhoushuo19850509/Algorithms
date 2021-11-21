package com.nbcb.algorithms.sort;

/**
 * Algorithm 2.6 Heap Priority
 * Heap Priority这个数据结构，是对Priority Queue的实现
 * 实现方式是binary heap
 *
 * binary heap的特点是：
 * 1.当前节点必须大于等于它的两个子节点；
 * 2.当前节点index为t，那么它的两个子节点index为2t,2t+1
 * 3.当前节点index为t，那么它的父节点为t/2
 */
public class MaxPQ <Key extends Comparable<Key>>{

    private Key[] pq;  // 承载priority queue的数据结构也是一个array
    private int N = 0;     // priority queue的大小 从pq[1]开始存放节点元素

    // constructor
    public MaxPQ(int maxSize) {
        pq = (Key[])new Comparable[maxSize + 1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    /**
     * 关键方法1 ： 插入一个元素
     * @param v
     */
    public void insert(Key v){
        // 把新插入的元素放到真个pq最后的位置
        pq[++N] = v;
        // 然后根据v的大小，不断让它上浮，直到达到合适的位置
        swim(N);
    }

    /**
     * 关键方法2 ： 取出priority Queue中最大的元素
     * @return
     */
    public Key delMax(){
        Key max = pq[1];  // root node是整个pq中最大的node
        exch(1,N--);    // 把这个pq中处于最底层、最后那个node和root node互换
        pq[N+1] = null;   // 释放最后这个位置的存储空间
        sink(1);       // 然后不断下潜这个从底层置换上来的节点，直到下潜到合适的位置
        return max;
    }

    /**
     * 内部私有方法：比较两个节点
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i , int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 内部私有方法：交换两个节点
     * @param i
     * @param j
     */
    private void exch(int i , int j){
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    /**
     * 当前节点pq[k]如果违反了binary heap的规则：父节点比pq[k]小
     * 那么pq[k]要不断往上浮，直到找到一个合适的位置
     * @param k
     */
    private void swim(int k){
        /**
         * 只要同时满足以下2个条件，pq[k]就要不断上浮到父节点的位置
         * 条件1 k > 1 (一旦k==1，说明pq[k]达到了root node，无须再上浮)
         * 条件2 k/2 < k (说明父节点比pq[k]小)
         */
        while(k > 1 && less(k/2, k)){
            exch(k, k/2); // 上浮到父节点的位置
            k = k/2;
        }
    }

    /**
     * 当前节点pq[k]如果违反了binary heap的规则：pq[k]比子节点小
     * 那么p[k]要不断下潜，直到找到一个合适的位置
     * @param k
     */
    private void sink(int k){
        // while循环的意思是，只要pq[k]还有子节点，就要不断尝试下潜：sink()
        while( 2*k <= N){
            int j = 2*k; // pq[j]是pq[k]左边的子节点
            // if：如果pq[k]存在两个子节点，就找出更大的那个
            if(j < N && less(j,j+1)){
                j++;
            }
            // if：如果pq[k] >= pq[j](pq[k]两个子节点中较大的那个)，就无需再下潜
            if(!less(k,j)){
                break;
            }
            // 既然pq[k] < pq[j](pq[k]两个子节点中较大的那个)，那就下潜
            // 下潜的方式是和子节点交换
            exch(k,j);
            k = j;
        }
    }

    // 打印PQ
    public void printPQ(){
        for (int i = 1; i < N; i++) {
            Key key = pq[i];
            System.out.println(key);
        }
    }

    /**
     * main方法用于验证MaxPQ功能是否正常
     * @param args
     */
    public static void main(String[] args) {
        String[] a = "S O R T E X A M P L E".split(" ");
        System.out.println("before sort");
        SortBase.show(a);

        // 初始化PQ，并且把元素一个个插入PQ中
        MaxPQ<String> pq = new MaxPQ<>(100);
        for(String str : a){
            pq.insert(str);
        }

        // 在调用delMax()清空PQ之前，先打印一下PQ各个节点
        pq.printPQ();

        // 调用delMax()，依照从大到小的顺序，打印PQ中的元素
        System.out.println("list pq: ");
        while(!pq.isEmpty()){
            System.out.println(pq.delMax());
        }
    }


}
