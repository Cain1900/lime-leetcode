package com.lime.leetcode.set400;

import java.util.Stack;

/**
 * Created by YuHang on 2023/7/17.
 */
public class AddStrings415 {

    public String addStrings(String num1, String num2) {
        StringBuilder stringBuilder = new StringBuilder();
        int more = 0;
        int i = num1.length() - 1, j = num2.length() - 1 ;
        while ( i >= 0 || j >= 0 || more > 0){
            int first = i >= 0? num1.charAt(i) - '0': 0;
            int second = j >= 0? num2.charAt(j) - '0': 0;
            int sum = first + second + more;
            stringBuilder.append(sum % 10);
            more = sum / 10;
            i--;
            j--;
        }
        if(more > 0){
            stringBuilder.append(more);
        }
        return stringBuilder.reverse().toString();
    }

    public String addStrings1(String num1, String num2) {
        Stack<Integer> stack1 = new Stack<>();
        for (int i = 0; i < num1.length(); i++){
            stack1.push(num1.charAt(i) - '0');
        }
        Stack<Integer> stack2 = new Stack<>();
        for (int i = 0; i < num2.length(); i++){
            stack2.push(num2.charAt(i) - '0');
        }
        StringBuilder stringBuilder = new StringBuilder();
        int more = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            int first = stack1.isEmpty()? 0: stack1.pop();
            int second = stack2.isEmpty()? 0: stack2.pop();
            int sum = first + second + more;
            stringBuilder.append(sum % 10);
            more = sum / 10;
        }
        if(more > 0){
            stringBuilder.append(more);
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "11";
        String num2 = "123";
        AddStrings415 addStrings415 = new AddStrings415();
        System.out.println(addStrings415.addStrings(num1, num2));
    }
}
