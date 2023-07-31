package com.lime.leetcode.set1100;

import java.util.*;

/**
 * Created by YuHang on 2023/4/28.
 */
public class DinnerPlates1172 {

    public static void main(String[] args) {
        DinnerPlates dinnerPlates = new DinnerPlates(2);
        dinnerPlates.push(1);
        System.out.println("null");
        dinnerPlates.push(2);
        System.out.println("null");
        dinnerPlates.push(3);
        System.out.println("null");
        dinnerPlates.push(4);
        System.out.println("null");
        dinnerPlates.push(5);
        System.out.println("null");
        System.out.println(dinnerPlates.popAtStack(0));
        dinnerPlates.push(20);
        System.out.println("null");
        dinnerPlates.push(21);
        System.out.println("null");
        System.out.println(dinnerPlates.popAtStack(0));
        System.out.println(dinnerPlates.popAtStack(2));
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
    }



    public static class DinnerPlates {
        int capacity;
        //数组栈
        List<Integer> stack;
        //每个栈的栈顶元素位置
        List<Integer> top;
        //被popAtStack()方法删除的元素位置
        TreeSet<Integer> poppedPos;

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            stack = new LinkedList<>();
            top = new ArrayList<>();
            poppedPos = new TreeSet<>();
        }

        public void push(int val) {
            if(poppedPos.isEmpty()){
                int pos = stack.size();
                stack.add(val);
                if (pos % capacity == 0) {
                    //栈容量已满，新建栈
                    //栈中有一个元素时，栈顶为0
                    top.add(0);
                }else {
                    //取栈个数
                    int stackPos = top.size() - 1;
                    top.set(stackPos, top.get(stackPos) + 1);
                }
            }else {
                //取出最小位置值，被删除的元素在数组中的下标
                int pos = poppedPos.pollFirst();
                //入栈
                stack.set(pos, val);
                //通过数组中的下标计算入栈的编号
                int index = pos / capacity;
                //修改栈顶位置
                top.set(index, top.get(index) + 1);
            }
        }

        public int pop() {
            while (!stack.isEmpty() && poppedPos.contains(stack.size()-1)){
                //栈中元素不为空，同时栈的最后一个元素被删除
                //移除栈中元素
                stack.remove(stack.size()-1);
                //取出被删除的元素在数组中的下标的最大值
                int pos = poppedPos.pollLast();
                if (pos % capacity == 0) {
                    //移除的元素是新栈第一个元素时，需要销毁栈的栈顶记录
                    top.remove(top.size()-1);
                }
            }
            if (stack.isEmpty()) {
                //栈为空
                return -1;
            }else {
                //栈不为空，且没有被删除
                int pos = stack.size() - 1;
                //即将被删除的元素值
                int val = stack.get(pos);
                //从栈中移除
                stack.remove(pos);
                //该值是最大下标值，所在栈位置最大
                int index = top.size() - 1;
                if (pos % capacity == 0) {
                    //移除的元素是新栈第一个元素时，需要销毁栈的栈顶记录
                    top.remove(index);
                } else {
                    //移除的元素不是栈的第一个元素，栈顶位置减一
                    top.set(index, index - 1);
                }
                return val;
            }
        }

        public int popAtStack(int index) {
            if(index >= top.size()){
                return -1;
            }
            int stackTop = top.get(index);
            if(stackTop < 0){
                //栈中没有元素
                return -1;
            }
            //设置新的栈顶
            top.set(index, stackTop - 1);
            //记录被删除的元素在数组中的下标
            int pos = index * capacity + stackTop;
            poppedPos.add(pos);
            //返回数组中的下标返回被删除的元素
            return stack.get(pos);
        }
    }

    public static class DinnerPlates1 {
        List<Stack<Integer>> stackList = new ArrayList<>();
        int capacity;
        //记录可以入栈的头节点
        Node head;
        //记录可以入栈的尾节点
        Node tail;

        public DinnerPlates1(int capacity) {
            //初始化栈容量
            this.capacity = capacity;
            head = new Node(0, null, null);
            tail = head;
            //初始化第一个栈列表
            stackList.add(head.stack);
        }

        public void push(int val) {
            //放入可以入栈的头节点
            head.stack.push(val);
            head.size++;
            if(head.size == capacity){
                //若容量已满
                if(head.next != null){
                    //设置新的头节点
                    head = head.next;
                }else {
                    //新建节点
                    head = new Node(stackList.size(), null, null);
                    tail = head;
                    //存入栈列表中
                    stackList.add(head.stack);
                }
            }
        }

        public int pop() {
            Stack<Integer> stack = stackList.get(stackList.size() - 1);
            if(!stack.isEmpty()){
                //尾节点始终未满
                tail.size--;
                return stack.pop();
            }else {
                //遍历列表，找到一个非空栈
                for (int i = stackList.size() - 2; i >= 0; i--){
                    stack = stackList.get(i);
                    if(!stack.isEmpty()){
                        if(stack.size() == capacity){
                            //加入到可入栈列表中
                            Node temp = tail;
                            while (temp.pre != null && temp.index > i){
                                temp = temp.pre;
                            }
                            if(temp.pre == null && temp.index >= i){
                                //新建头节点
                                Node item = new Node(i, null, temp, stack);
                                temp.pre = item;
                                head = item;
                            }else if(temp.index < i){
                                Node item = new Node(i, temp, temp.next, stack);
                                temp.next.pre = item;
                                temp.next = item;
                            }
                        }
                        //尾节点始终未满
                        return stack.pop();
                    }
                }
            }
            return -1;
        }

        public int popAtStack(int index) {
            if(index > stackList.size() - 1){
                return -1;
            }
            Stack<Integer> stack = stackList.get(index);
            if(stack.isEmpty()){
                return -1;
            }
            if(stack.size() == capacity){
                //加入到可入栈列表中
                Node temp = tail;
                while (temp.pre != null && temp.index > index){
                    temp = temp.pre;
                }
                if(temp.pre == null && temp.index >= index){
                    //新建头节点
                    Node item = new Node(index, null, temp, stack);
                    temp.pre = item;
                    head = item;
                }else if(temp.index < index){
                    //插入新节点
                    Node item = new Node(index, temp, temp.next, stack);
                    temp.next.pre = item;
                    temp.next = item;
                }
            }
            return stack.pop();
        }

        class Node{
            int index;
            Stack<Integer> stack;
            int size;
            Node pre;
            Node next;

            public Node(int index, Node pre, Node next) {
                this.index = index;
                this.pre = pre;
                this.next = next;
                this.stack = new Stack<>();
            }

            public Node(int index, Node pre, Node next, Stack<Integer> stack) {
                this.index = index;
                this.pre = pre;
                this.next = next;
                this.stack = stack;
                this.size = 0;
            }
        }
    }
}
