package com.nbcb.algorithms.basic;


import java.util.Iterator;

/**
 * Algorithm 1.3
 * 通过基础的node list，实现Queue这样高级的数据结构
 */
public class Queue<Item> implements Iterable<Item> {

    // 需要特别注意，Queue初始化的时候，first/last node都是指向null的
    private Node first;  // header of the queue ，first pushed node
    private Node last;   // tail of the queue , last pushed node
    private int N = 0;

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return first == null;
    }


    // 定义Queue的节点对象： Node
    private class Node{
        Item item;
        Node next;
    }

    /**
     * push an item to the tail of the Queue
     * enqueue的本质就是创建一个新的last节点
     * @param item
     */
    public void enqueue(Item item){
        Node oldLast = this.last;
        last = new Node();  // 创建一个新的last节点
        last.item = item;
        last.next = null;

        // 有两种情况，
        if(isEmpty()){
            /**
             * 如果是第一次enqueu，那么要特殊处理
             * first/last都指向这个新创建的last node
             */
            first = last;
        }else{
            /**
             * 如果不是第一次enqueu，把新创建的last，
             * 作为oldLast节点的next node
             */
            oldLast.next = last;
        }
        N++;
    }

    // get an item from the header of the queue
    public Item dequeue(){

        // 如果Queue为空，直接返回NULL
        if(isEmpty()){
            return null;
        }

        Item item = first.item;
        first = first.next;  // first node指向下一个节点

        /**
         * first node指向下一个节点之后，Queue变为空
         * 说明本次dequeue的是最后一个节点，要特殊处理
         */
        if(isEmpty()){
            last = null;
        }
        N--;
        return item;
    }

    // TODO 补充iterable接口

    // 实现Iterable接口
    @Override
    public Iterator<Item> iterator() {
        return new Queue.ReverseListIterator();
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
    private class ReverseListIterator implements Iterator<Item>{

        Node currentNode = first;
        int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            --i;
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    /**
     * main方法主要是验证Queue的功能
     * @param args
     */
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();

        for (int i = 0; i < 10; i++) {
            String element = String.valueOf(i);
            queue.enqueue(element);
        }

        // dequeue()方式遍历queue
        String str = "";
        while( null != (str = queue.dequeue()) ){
            System.out.println("dequeue() element from Queue: " + str);
        }
        System.out.println("finish pop() ,current size of Queue: "
                + queue.size());


        for (int i = 0; i < 10; i++) {
            String element = String.valueOf(i);
            queue.enqueue(element);
        }

        Iterator<String> iterator = queue.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("finish iterator through Queue ,current size of stack: " + queue.size());
    }

}
