package com.lime.leetcode.set900;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by YuHang on 2023/5/5.
 */
public class PowerfulIntegers970 {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<Integer>();
        int value1 = 1;
        while (value1 <= bound) {
            int value2 = 1;
            while (value2 <= bound) {
                int value = value1 + value2;
                if (value <= bound) {
                    set.add(value);
                }
                value2 *= y;
                if(value2 == 1){
                    break;
                }
            }
            value1 *= x;
            if(value1 == 1){
                break;
            }
        }
        return new ArrayList<Integer>(set);
    }

    public static void main(String[] args) {
        PowerfulIntegers970 powerfulIntegers970 = new PowerfulIntegers970();
        System.out.println(powerfulIntegers970.powerfulIntegers(2, 3, 10));
    }
}
