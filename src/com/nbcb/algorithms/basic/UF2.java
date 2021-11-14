package com.nbcb.algorithms.basic;

/**
 * Algorithm 1.5
 * Union find implementation(version2.0 quick union)
 *
 * 我们在UF.java(version1.0)中已经实现了UF的一种基础算法
 * 基本原理是，id[n]保存的是节点N所属的component id
 * 这个算法的一个问题是，union需要遍历id[] array，效率稍微有点低
 *
 * UF2.java版本的实现中，设计了一种快速union的算法
 * 这个算法和UF.java差不多，唯一需要重构的方法是find()/union()
 *
 * 这个算法的基本原理是这样的：
 * 1.id[n]保存的是节点n节点挂靠的父节点
 * 2.find(n)是查找节点n的根节点
 * 3.union(p,q)分别合并节点p和节点q的根节点
 * 4.节点n所属的component中，各个节点以树的形式进行组织，
 *   树的根节点是component id
 *
 * 这个算法的特点是，提升了union(p,q)的速度，
 * 不用像UF.java中遍历array中各个节点
 *
 */
public class UF2 {

    int[] id;  // array
    int count; // count of component number

    /**
     * constructor of UF class 各种初始化动作
     * @param N 有多少数据需要做UF操作
     */
    public UF2(int N) {
        // 把所有点的信息读取进来
        id = new int[N];
        count = N;

        /**
         * 初始化数组
         * 初始化的时候，id[i]代表的component id默认就是i
         */
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    /**
     * 指定p/q这两个点连通起来
     * 这个方法是整个UF算法的核心
     * 这个算法的性能，决定了整个UF算法的性能
     * @param p
     * @param q
     */
    public void union(int p, int q){



        // 分别找到p/q的根节点
        int pRoot = find(p);
        int qRoot = find(q);

        // 如果p/q的根节点一样，说明这两个节点是连通的，那就直接返回
        if( pRoot == qRoot){
            return;
        }

        /**
         * 否则的话，就把q节点所属的component和p节点所属的component进行合并
         * 合并逻辑是，把q节点所属的component的根节点，
         * 作为p节点所属的component的根节点的父节点
         * 具体参考Page225的示例图
         */
        id[pRoot] = qRoot;

        // 一旦有新的两个点连通，整体component数量就少一个
        count--;
    }

    /**
     * 判断两个点是否属于同一个组件
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q){
        if( find(p) == find(q)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 某个点属于哪个组件
     *
     * 在2.0中find(p)的目标是找到节点p所属的component的根节点
     *
     * find()基本逻辑为：
     * 从节点p开始不断往上追溯父节点，直到找到根节点为止
     * @param p
     * @return
     */
    public int find(int p){

        /**
         * 其中id[p]的意思是节点p挂载的根节点
         * 如果p等于id[p],说明达到了根节点
         */
        while(p != id[p]){
            /**
             * 如果p不等于id[p]，说明还没有达到根节点
             * 那就把p指向它的父节点，继续找
             */
            p = id[p];
        }

        return id[p];
    }

    /**
     * 这个点的集合分成多少内部连通的组件(component)
     * @return
     */
    public int count(){
        return this.count;
    }

    /**
     * main方法验证我们的UF方法，是否正确
     * @param args
     */
    public static void main(String[] args) {
        UF uf = new UF(10);
        uf.union(4,3);
        uf.union(3,8);
        uf.union(6,5);
        uf.union(9,4);
        uf.union(2,1);
        uf.union(5,0);
        uf.union(7,2);
        uf.union(6,1);
        System.out.println(uf.count());
    }
}
