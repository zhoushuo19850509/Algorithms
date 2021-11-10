package com.nbcb.algorithms.basic;


import com.sun.corba.se.spi.ior.IORTemplate;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/**
 * Algorithm 1.1 ResizingArrayStack
 * 这个程序虽小，但是完整演示了如何通过array这样的基础数据结构，
 * 来实现Stack这样的高级集合类
 * 像java这样的高级语言，其实也是由array这样的基础数据结构，配合一些算法
 * 慢慢构建起来的，各种语法糖，也是像搭积木一样，由一些基础功能搭建起来的
 *
 * 这个程序的特色是：
 * 1.能根据Stack中实际元素数量，动态调整array的大小
 * 2.能够通过iterator接口遍历stack中的元素(from top to bottom)，而不影响stack元素内容
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{


    /**
     * 保存Stack数据的基础数据结构
     * 因为是Stack内部使用的，所以是private
     * 初始化值给了一个Item大小的空间
     */
    private Item[] a = (Item[]) new Object[1];
    private int N = 0; // the current size of stack

    public int size(){
        return N;
    }

    /**
     * resize()方法用于动态调整array大小
     * 防止array太大导致空间浪费；
     * 防止array过小导致不够用
     * @param maxSize
     */
    public void resize(int maxSize){
        // step1 创建一个新的array
        Item[] tmpArray = (Item[]) new Object[maxSize];

        // step2 把原来array中的元素，全部复制到新创建的array中去
        for (int i = 0; i < N; i++) {
            tmpArray[i] = a[i];
        }

        // step3 把我们内部的array指向新的array(老的array会由Java自动销毁)
        a = tmpArray;

        System.out.println("trigger resize() old size of stack: " +
                "[" + N + "] and new size : [" + maxSize + "]");
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void push(Item item){
        /**
         * push前先要判断一下当前的array是否已经满了
         * 如果已经满了，就要扩大一下array(一倍)
         */
        if(N == a.length){
            this.resize( 2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop(){
        /**
         * pop前先要判断一下array中是否有元素
         * 如果array是空的，就返回null
         */
        if(isEmpty()){
            return null;
        }

        /**
         * pop前还要检测一下array中的元素，
         * 如果array中元素过少(少于array 1/4)，就要缩小一下array
         * 防止array空间浪费
         */
        if( N > 0 && N < a.length / 4){
            this.resize( a.length / 2);
        }

        Item topItem = a[--N];
        a[N] = null;  // 将a[N]指向null，但是不会影响topItem的值
        return topItem;
    }




    // 实现Iterable接口
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * the ReverseArrayIterator iterator
     * from the top of stack to the bottom
     * pay attention : the iterator does not change
     * the node content of the stack
     *
     * 备注：需要注意的是，iterator方式遍历stack，
     * 只是遍历了元素内容，并不会把元素从stack pop()出来
     */
    private class ReverseArrayIterator implements Iterator<Item>{

        private int i = N;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {

        }
    }

    /**
     * main方法，对我们的ResizingArrayStack的功能进行验证
     * 1.验证Stack基本功能(pop()/push())是否正常；
     * 2.验证动态调整size的功能是否正常
     * 3.验证stack的iterator功能
     * @param args
     */
    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();


        // pop()方式遍历stack
        for (int i = 0; i < 10; i++) {
            String element = String.valueOf(i);
            stack.push(element);
        }

        String str = "";
        while( null != (str = stack.pop()) ){
            System.out.println("pop() element from stack: " + str);
        }
        System.out.println("finish pop() ,current size of stack: " + stack.size());


        // iterator through stack ： from top to the bottom
        for (int i = 0; i < 10; i++) {
            String element = String.valueOf(i);
            stack.push(element);
        }


        Iterator<String> iterator = stack.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("finish iterator through stack ,current size of stack: " + stack.size());

    }
}
