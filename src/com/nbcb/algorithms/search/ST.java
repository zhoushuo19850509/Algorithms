package com.nbcb.algorithms.search;

/**
 * interface of ST ADT
 * 包含了ST ADT主要的接口方法
 */
public interface ST<Key , Value> {
    void put(Key key, Value value);
    Value get(Key key);
    void delete(Key key);
    boolean contains(Key key);
    boolean isEmpty();
    int size();
    Iterable<Key> keys();
}
