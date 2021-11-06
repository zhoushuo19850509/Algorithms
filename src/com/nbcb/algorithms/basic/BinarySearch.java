package com.nbcb.algorithms.basic;

/**
 * binary search
 * 整体思路还是比较清晰的
 */
public class BinarySearch {


    /**
     * binary search算法，从一个(已经完成排序)数组中，找出某个key
     * 如果不存在key，就返回-1
     * @param key
     * @param a
     * @return
     */
    public static int rank(int key , int[] a){
        // 初始化low position/high position
        int lo = 0;
        int hi = a.length -1;

        while( lo <= hi ){
            int mid = lo + ( hi - lo ) / 2;
            if( key < a[mid]){
                hi = mid - 1;
            }else if(key > a[mid]){
                lo = mid + 1;
            }else{
                // 找到了key的位置
                return mid;
            }
        }
        return -1;
    }

    // 在main()中验证binary search功能
    public static void main(String[] args) {
        System.out.println("start search...");

        // 先准备一个数组
        int[] whiteList = new int[]{12,14,25,27,31,33,36,48,55,67,72};

        int key = 33;
        int result = rank(key,whiteList);
        System.out.println("binary search result : " + result );
    }
}
