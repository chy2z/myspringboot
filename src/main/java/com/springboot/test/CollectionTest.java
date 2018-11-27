package com.springboot.test;

import java.util.*;

/**
* @Title: CollectionTest
* @Description: 集合测试
* @author chy
* @date 2018/8/2 21:35
*/
public class CollectionTest {


    public static void main(String[] args) {

        /**
         * 非同步
         * 元素超过size时，容量扩容50%
         * 非同步、非频繁删除时选择
         * 有序
         */
        ArrayList arrayList=new ArrayList();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);


        arrayList.stream().forEach(System.out::println);

        System.out.println("ArrayList End");


        /**
         * 线程同步
         * 元素超过size时，容量扩容1倍
         * 有序
         */
        Vector vector=new Vector();

        vector.add(1);
        vector.add(2);
        vector.add(3);

        vector.stream().forEach(System.out::println);

        System.out.println("Vector End");


        /**
         * 非同步
         * 频繁在任意位置插入、删除时选择
         * 有序
         */
        LinkedList linkedList=new LinkedList();


        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);


        linkedList.forEach(System.out::println);


        System.out.println("LinkedList End");


        /**
         * 红黑树
         * 按key的自然顺序排序
         * 有序
         */
        TreeMap treeMap=new TreeMap();

        treeMap.put("1",1);
        treeMap.put("2",2);
        treeMap.put("3",3);

        Iterator it = treeMap.keySet().iterator();
        while (it.hasNext()) {
            System.out.println(treeMap.get(it.next()));
        }


        System.out.println("TreeMap End");

        /**
         *
         * 容器内key唯一
         */
        Set<Integer> set=new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.forEach(System.out::println);

        System.out.println("Set End");


        /**
         * 树形结构
         * 元素按字符串顺序排序存储
         */
        TreeSet<Integer> treeSet=new TreeSet<>();

        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);

        treeSet.forEach(System.out::println);

        System.out.println("TreeSet End");


        /**
         * 线程不安全
         * 支持支持 key=null 和 value=null
         * 无序
         */
        HashMap hashMap=new HashMap();
        hashMap.put("1",1);
        hashMap.put("2",2);
        hashMap.put("3",3);
        hashMap.put(null,null);

        hashMap.forEach((k,v)->System.out.println("key : " + k + "; value : " + v));

        System.out.println("HashMap End");

        /**
         * 线程安全
         * 不支持 key=null 和 value=null
         */
        Hashtable hashtable=new Hashtable();
        hashtable.put("1",1);
        hashtable.put("2",2);
        hashtable.put("3",3);

        hashtable.forEach((k,v)->System.out.println("key : " + k + "; value : " + v));

        System.out.println("Hashtable End");
    }

}
