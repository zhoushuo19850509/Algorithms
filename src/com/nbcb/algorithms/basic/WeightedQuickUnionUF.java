package com.nbcb.algorithms.basic;

/**
 * Algorithm 1.5
 * Union find implementation(version3.0 quick union)
 *
 * 我们在UF.java(version1.0)中已经实现了UF的一种基础算法(quick-find)
 * 在UF2.java(version2.0)中实现quick-union算法
 * 主要原理是通过树形的形式组织component中各个节点，提升find()/union()效率
 * 尤其是union()，只要通过赋值，就能完成两个component的合并，避免了遍历多个节点
 *
 * 但是在2.0中有一个极端情况，就是我们union(p,q)是固定把p的根节点挂载到q的根节点。
 * 如果p所属的component一直很大，而q一直很小的话，union性能比较低。
 * 问题本质是p所属的component树结构问题：不是扁平化的树结构，导致find()效率较低。
 *
 * 优化原理
 * 为了避免出现2.0中出现的union的两个component树结构不均衡导致find()效率低的问题，
 * 我们优化一下，就是给每个component标识一下size(树中节点的数量)
 * union(p,q)方法不再是固定把p的根节点挂载到q的根节点
 * 而是把size较小的树挂载到size较大的树，这样就尽量均衡了树结构
 *
 * 代码调整：
 * 1.新增属性： int[] sz 标识各个component的size
 * 2.优化union(p,q)方法
 */
public class WeightedQuickUnionUF {
    int[] id;  // array
    int count; // count of component number
    int[] sz;  // size of each component

    /**
     * constructor of UF class 各种初始化动作
     * @param N 有多少数据需要做UF操作
     */
    public WeightedQuickUnionUF(int N) {
        // 把所有点的信息读取进来
        id = new int[N];
        sz = new int[N];
        count = N;

        /**
         * 初始化数组
         * 初始化的时候，id[i]代表的component id默认就是i
         */
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }

        /**
         * 初始化各个component的size，初始值为1
         */
        for (int i = 0; i < id.length; i++) {
            sz[i] = 1;
        }
    }

    /**
     * 指定p/q这两个点连通起来
     * 这个方法是整个UF算法的核心
     * 这个算法的性能，决定了整个UF算法的性能
     * 针对version2.0中的极端情况，做出了优化
     * 不再像version2.0一样，固定把p的根目录挂载到q的根目录、
     * 而是把size较小的component挂载到size较大的component根节点下
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
         * 合并逻辑是，判断p/q节点所属的component的size
         * 把size较小的component挂载到size较大的component下
         * 具体参考Page226的示例图
         */
        if(sz[p] < sz[q]){
            id[p] = q;
            sz[q] += sz[p];
        }else{
            id[q] = p;
            sz[p] += sz[q];
        }

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
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
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
