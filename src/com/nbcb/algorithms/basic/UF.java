package com.nbcb.algorithms.basic;

/**
 * Algorithm 1.5
 * Union find implementation
 *
 * 所谓的union find，就是给定N个点，然后给定一组两点组合信息
 *
 * 目标
 * 1.count()
 * 让你判断这个点的集合分成多少内部连通的组件(component)
 * 2.find(int p)
 * 然后让你判断某个点属于哪个组件
 * 3.connected(int p, int q)
 * 判断两个点是否属于同一个组件
 *
 * 算法大致原理
 * 其实就是把N个点放到一个数组中，点n是数组index
 * 数组元素是该点对应的compoent标识，
 * 比如id[n]的值就是点n对应的component标识
 * 一旦两个点(p/q)连通了，就把p所属的component中所有点的数据改成id[q]
 */
public class UF {

    int[] id;  // array
    int count; // count of component number

    /**
     * constructor of UF class 各种初始化动作
     * @param N 有多少数据需要做UF操作
     */
    public UF(int N) {
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

        // 如果p/q本身就是连通的，那就什么都不做，直接返回
        if(connected(p, q)){
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if(id[i] == id[p]){
                id[i] = id[q];
            }
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
     * @param p
     * @return
     */
    public int find(int p){
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
