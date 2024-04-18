package com.lime.leetcode.set100;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2023/9/25.
 */
public class LRUCache146 {

    /**最久未使用*/
    private int capacity;
    private int size;
    CacheNode head;
    CacheNode tail;
    Map<Integer, CacheNode> map = new HashMap<>();

    public LRUCache146(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new CacheNode();
    }

    private void addToHead(CacheNode cacheNode){


    }

    private void removeTail(CacheNode cacheNode){

    }

    private void moveToHead(CacheNode cacheNode){


    }

    public int get(int key) {
        if(map.containsKey(key)){
            CacheNode cacheNode = map.get(key);
            if(head != cacheNode){
                cacheNode.pre.next = cacheNode.next;
                cacheNode.next.pre = cacheNode.pre;
                cacheNode.next = head;
                head.pre = cacheNode;
                cacheNode.pre = null;
                head = cacheNode;
            }
            return cacheNode.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            CacheNode cacheNode = map.get(key);
            if(head != cacheNode){
                cacheNode.pre.next = cacheNode.next;
                cacheNode.next.pre = cacheNode.pre;
                cacheNode.next = head;
                head.pre = cacheNode;
                cacheNode.pre = null;
                head = cacheNode;
            }
            cacheNode.value = value;
        }else {
            CacheNode cacheNode = new CacheNode(key, value, head);
            head.pre = cacheNode;
            head = cacheNode;
            map.put(key, cacheNode);
            if(size < capacity){
                size++;
            }else {
                tail  = tail.pre;
                tail.next = null;
            }
        }

    }

    private class CacheNode{
        int key;
        int value;
        CacheNode pre;
        CacheNode next;

        public CacheNode(int key, int value, CacheNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public CacheNode() {

        }
    }

}
