package com.nbcb.algorithms.basic;


import java.util.Iterator;

/**
 * Algorithm 1.2
 */
public class Stack<Item> implements Iterable<Item>{

    // top node of the stack
    private Node firstNode;

    // node number of current stack
    private int N = 0;

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    // 定义list的节点对象： Node
    private class Node{
        Item item;
        Node next;
    }

    // 开始定义stack相关的方法： push()/pop()
    public void push(Item item){
        // 先让一个临时node指向firstNode
        Node oldFirstNode = firstNode;
        // 再new 一个新的Node，用来保存新push 的item数据
        firstNode = new Node();
        firstNode.item = item;
        // 最后，新new的node，就作为top node of the stack
        firstNode.next = oldFirstNode;
        N++;
    }

    public Item pop(){

        if(isEmpty()){
            return null;
        }

        Item item = firstNode.item;

        /**
         * 然后把firstNode指向next node of the firstNode
         * 这样看起来，firstNode感觉像是一个指针对象
         * 总是指向top node of the stack
         */
        firstNode = firstNode.next;
        N--;

        return item;
    }

    // 实现Iterable接口
    @Override
    public Iterator<Item> iterator() {
        return new Stack.ReverseListIterator();
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

        Node currentNode = firstNode;
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


    // 验证我们新建的Stack对象的功能
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();

        for (int i = 0; i < 10; i++) {
            String element = String.valueOf(i);
            stack.push(element);
        }

        // pop()方式遍历stack
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
        System.out.println("finish push ,current size: " + stack.size());

        Iterator<String> iterator = stack.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("finish iterator through stack ,current size of stack: " + stack.size());

    }


}
