package com.nbcb.algorithms.search;

import edu.princeton.cs.algs4.StdIn;

import java.io.*;
import java.util.Iterator;

/**
 * 这个文件是用来检验ST的功能
 * 步骤如下：
 * 1.读取一个文件
 * 2.遍历这个文件的各个word
 * 3.统计各个word put的数量
 * 4.统计结果放在一个ST数据结构中
 * 5.其中key: word  / value: word在文件中出现的次数
 *
 * 备注：可以读取的一个实际文件为：
 * /Users/zhoushuo/Documents/tmp/a.txt
 * 这个文件是从《Indtroduction to Algorithms》这本书中复制过来的
 */
public class FrequencyCounter {
    public static void main(String[] args) {

        ST<String, Integer> st = new SequentialSearchST<>();
        int minLen = 2;  //字符串长度至少达到minLen，否则不予统计

        String filePath = "/Users/zhoushuo/Documents/tmp/a.txt";
        File file = new File(filePath);
        InputStreamReader reader = null;
        BufferedReader br = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            reader = new InputStreamReader(fis);
            br = new BufferedReader(reader);
            String line = "";
            long startPut = System.currentTimeMillis();
            while( (line= br.readLine()) != null){
                String[] words = line.split(" ");

                for(String word: words){
                    // 先把word中的字符替换掉 后续改为正则表达式
                    word = word.replace(".","");
                    word = word.replace(",","");
                    word = word.replace("(","");
                    word = word.replace(")","");
                    word = word.replace(":","");
                    word = word.replace(";","");

                    // TODO 把那些非字符的筛选掉

                    // 然后对word长度做一轮筛选
                    if(word.length() < minLen){
                        continue;
                    }

//                    System.out.println(word);
                    if(!st.contains(word)){
                        st.put(word,0);
                    }else{
                        st.put(word, st.get(word) + 1);
                    }
                }

            }
            long endPut = System.currentTimeMillis();
            System.out.println("put into ST time cost: " +
                    ( endPut - startPut) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //统计哪个word在整个文档中出现次数最多
        long startCountMax = System.currentTimeMillis();
        String maxWord = "";
        st.put(maxWord,0);
        for(String key: st.keys()){
            if(st.get(key) > st.get(maxWord)){
                maxWord = key;
            }
        }
        long endCountMax = System.currentTimeMillis();
        System.out.println("统计耗时： " + (endCountMax - startCountMax) + " ms");
        System.out.println(maxWord + " " + st.get(maxWord));
    }
}
