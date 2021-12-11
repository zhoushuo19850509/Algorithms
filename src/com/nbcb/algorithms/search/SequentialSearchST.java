package com.nbcb.algorithms.search;

import java.util.Iterator;

/**
 * Algorithm 3.1
 * 这个文件是ST的一种实现，主要思路比较简单，
 * 就是通过list保存key/value的值
 * 要实现get(key)接口，就是遍历这个list
 * 显然这个ST实现的效率应该不会很高
 */
public class SequentialSearchST <Key, Value> implements ST<Key, Value>{

    private Node first; // list第一个节点
    int N = 0;  // ST中有多少key/value节点

    /**
     * 这个内部类，主要用来保存key/value数据
     */
    private class Node{
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * get()方法的实现思路，就是遍历list，直到找到key对应的value为止
     * @param key
     * @return
     */
    public Value get(Key key){
        for(Node x=first; x != null; x=x.next){
            if(x.key.equals(key)){
                return x.value;
            }
        }
        return null;  // get()方法根据key找不到value
    }

    /**
     * put()方法的实现思路，先遍历一下list，看要put的Key在list中是否已经存在
     * 如果已经存在，就拿最新的value去更新这个node
     * 如果不存在，就往list中插入一个新的node
     * @param key
     * @param value
     */
    public void put(Key key, Value value){
        for(Node x=first; x != null; x=x.next){
            if(x.key.equals(key)){
                x.value = value;
                return;
            }
        }


        /**
         * 如果程序跑到这里，说明要put的这个key/value在list中不存在
         * 那就创建一个新的node，保存key/value
         * first node就指向这个新创建的节点
         */
        first = new Node(key,value,first);
        N++;
    }


    public boolean contains(Key key){
        return null != get(key);
    }




    /**
     * ST是否为空
     * @return
     */
    public boolean isEmpty(){
        return N == 0;
    }

    /**
     * ST的长度
     * @return
     */
    public int size(){
        return N;
    }

    /**
     * 删除某个key/value
     * TODO
     * @param key
     */
    public void delete(Key key){

    }

    public Iterable<Key> keys(){

        return new SequentialSearchST.KeyIterable();
    }

    private class KeyIterable implements Iterable<Key>{

        @Override
        public Iterator<Key> iterator() {
            return new SequentialSearchST.KeyIterator();
        }
    }

    private class KeyIterator implements Iterator<Key> {

        Node currentNode = first;
        int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Key next() {
            --i;
            Key key = currentNode.key;
            currentNode = currentNode.next;
            return key;
        }

        @Override
        public void remove() {

        }
    }

    /**
     * 验证SequentialSearchST功能
     * @param args
     */
    public static void main(String[] args) {

        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        st.put("a",1);
        st.put("b",2);
        st.put("c",3);
        st.put("d",4);
        st.put("e",5);
        st.put("f",6);


        System.out.println(">>>>>>>>>");
        for(String key: st.keys()){
            System.out.println(key);
        }

    }
}
