package com.lime.leetcode.week;

/**
 * Created by YuHang on 2023/8/19.
 */
public class NumberOfBeautifulIntegers8013 {

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        int count  = 0;
        for (int i = low / k * k; i <= high; i = i + k){
            if(i >= low && checkEven(i)){
                count++;
            }
        }
        return count;
    }

    public int numberOfBeautifulIntegers1(int low, int high, int k) {
        int count  = 0;
        for (int i = low / k * k; i <= high; i = i + k){
            if(i >= low && checkEven(i)){
                count++;
            }
        }
        return count;
    }

    public boolean checkEven(int i){
        int evenNum = 0;
        int oddNum = 0;
        while (i > 0){
            if(i % 10 % 2 == 0){
                evenNum++;
            }else {
                oddNum++;
            }
            i /= 10;
        }
        if(evenNum == oddNum){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        NumberOfBeautifulIntegers8013 numberOfBeautifulIntegers8013 = new NumberOfBeautifulIntegers8013();
        System.out.println(numberOfBeautifulIntegers8013.numberOfBeautifulIntegers(10, 20, 3));
        System.out.println(numberOfBeautifulIntegers8013.numberOfBeautifulIntegers(10, 1000000000, 3));
    }
}
