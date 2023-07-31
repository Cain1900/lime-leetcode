package com.lime.leetcode.set1000;

import java.util.Stack;

/**
 * Created by YuHang on 2023/5/5.
 */
public class IsValid1003 {

    public boolean isValid(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'c' && length >= 2 && stringBuilder.substring(length - 2).equals("ab")){
                stringBuilder.delete(length - 2, length);
                length -= 2;
            }else {
                stringBuilder.append(c);
                length ++;
            }
        }
        return length == 0;
    }

    public boolean isValid01(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        if(s.length() <= 2){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        stack.push(s.charAt(1));
        for (int i = 2; i < s.length(); i++){
            if(s.charAt(i) == 'c' && stack.size() >= 2){
                Character c1 = stack.pop();
                Character c2 = stack.pop();
                if(c1 != 'b' && c2 != 'a'){
                    stack.push(c2);
                    stack.push(c1);
                    stack.push(s.charAt(i));
                }
            }else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "abacbcabcc";
        IsValid1003 isValid1003 = new IsValid1003();
        System.out.println(isValid1003.isValid(s));
    }

}
