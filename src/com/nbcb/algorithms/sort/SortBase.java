package com.nbcb.algorithms.sort;

public abstract class SortBase {

    /**
     * sort算法实现 由各个子类实现
     * @param a
     */
    public abstract void sort(Comparable[] a);

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
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static String printArray(Comparable[] a){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append("[" + i + "]" +a[i].toString());
        }
        return sb.toString();
    }

    public static String printArray(Comparable[] a , int lo, int hi){
        StringBuilder sb = new StringBuilder();
        for (int i = lo; i <= hi; i++) {
            sb.append(a[i].toString());
        }
        return sb.toString();
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

}
