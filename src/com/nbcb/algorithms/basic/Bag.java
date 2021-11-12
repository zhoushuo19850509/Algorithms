package com.nbcb.algorithms.basic;

import java.util.Iterator;

/**
 * Algorithm 1.4
 * 类似Stack这样的数据接口，LIFO
 * Bag.add()和Stack.push()类似
 * 但是没有pop()接口
 * Iterator规则： from the newly added node
 */
public class Bag<Item> implements Iterable<Item>{

    private Node first;   // the first node of the Bag
    private int N = 0;

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void add(Item item){
        Node oldNode = first;  // oldNode指向当前first节点
        first = new Node();    // 创建一个新的节点
        first.item = item;
        first.next = oldNode;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Bag.ListIterator();
    }

    private class ListIterator implements Iterator<Item>{

        Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    // 定义Queue的节点对象： Node
    private class Node{
        Item item;
        Node next;
    }

    /**
     * main()方法为了验证Bag对象的功能
     * @param args
     */
    public static void main(String[] args) {
        Bag<String> bag = new Bag<String>();

        for (int i = 0; i < 10; i++) {
            String element = String.valueOf(i);
            bag.add(element);
        }

        Iterator<String> iterator = bag.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("finish iterator through Bag ," +
                "current size of stack: " + bag.size());
    }
}
