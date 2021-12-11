package com.nbcb.algorithms.search;

import java.util.Iterator;

/**
 * Algorithm 3.2
 * ST ADT的第二种实现 BinarySearchST
 *
 * 这个ST ADT以array作为key/value键值对的存储容器
 * 其中put()/get()都是通过binary search的方式进行，
 * 啥意思呢？比如put(key,value)，我们要把这个键值对放到合适的位置
 * 先通过key对key所在的array进行binary search查询，找到合适的key的位置
 *
 * 这大大提升了put()/get()的效率
 */
public class BinarySearchST<Key extends Comparable<Key>, Value>
        implements ST<Key, Value>{
    private Key[] keys;         // array1 保存ST的keys
    private Value[] values;     // array2 保存ST的values
    private int N;  // ST中key/value键值对的数量

    /**
     * constructor of BinarySearchST
     * 主要是进行初始化
     * @param capacity
     */
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        N = 0;
    }

    @Override
    public void put(Key key, Value value) {
        int i = rank(key);
        // 要put()的key已经存在，那么就用最新的value更新
        if(i < N && keys[i].compareTo(key) == 0){
            values[i] = value;
            return;
        }else{ // 要put()的key不存在，那就插入新的key/value键值对
            // 插入新key/value键值对之前，先把后面的那些元素往后挪一个，
            // 给新的key/value腾出空间来
            for(int j = N; j > i; j--){
                keys[j] = keys[j-1];
                values[j] = values[j-1];
            }
            keys[i] = key;
            values[i] = value;
            N++;
        }
    }

    @Override
    public Value get(Key key) {
        // 空的ST，直接返回Null
        if(isEmpty()){
            return null;
        }
        int i = rank(key);
        // 刚好找到我们要get的键值对
        if(i < N && keys[i].compareTo(key) == 0){
            return values[i];
        }else{
            return null;
        }
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        return new BinarySearchST.KeyIterable();
    }

    /**
     * 这个rank()方法是整个BinarySearchST的核心
     * 意思是这样的，通过binary search的方法，从key array中找到适合放key的位置
     * 如果能够找到key，那么直接返回key的array index
     * 如果找不到key，就返回key应该放置的位置
     * @param key
     * @return 看情况，有可能返回key的准确位置(keys[result]刚好等于key)；
     *     也有可能返回key应该放的位置(keys[result]刚好比key大)
     */
    public int rank(Key key){
        int lo = 0;
        int hi = N - 1;
        while(lo <= hi){
            int mid = lo + ( hi - lo);
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0){
                hi = mid - 1;
            }else if(cmp > 0){
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return lo;
    }

    private class KeyIterable implements Iterable<Key>{

        @Override
        public Iterator<Key> iterator() {
            return new BinarySearchST.KeyIterator();
        }
    }

    private class KeyIterator implements Iterator<Key> {

        int i = 0;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Key next() {
            return keys[i++];
        }

        @Override
        public void remove() {

        }
    }

    /**
     * 这个main方法用来验证BinarySearchST的功能
     * @param args
     */
    public static void main(String[] args) {
        int CAPACITY = 10000;
        ST<String, Integer> st = new BinarySearchST<String, Integer>(CAPACITY);
        st.put("zs",2);
        st.put("hob",5);
        st.put("helen",12);
        st.put("jack",23);
        st.put("Allent",62);

        for(String key: st.keys()){
            System.out.println("" + key + " : " + st.get(key));
        }
    }
}
