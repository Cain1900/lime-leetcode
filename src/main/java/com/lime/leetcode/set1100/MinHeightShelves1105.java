package com.lime.leetcode.set1100;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/4/23.
 */
public class MinHeightShelves1105 {

    public int minHeightShelves(int[][] books, int shelfWidth) {
        //minHeight[i]记录存放第i本书的高度
        int[] minHeight = new int[books.length + 1];
        //初始化所有值
        Arrays.fill(minHeight, Integer.MAX_VALUE);
        //没有书时，高度为0
        minHeight[0] = 0;
        for (int i = 0; i < books.length; i++){
            //新建一层，将之前放置的书向这一层移动，计算这一层的高度
            //之前放置的书向上移动之后，书架高度会降低甚至消失
            //比较新建高度和之前书剩余高度，计算最小值
            int maxHeight = 0, curWidth = 0;
            for (int j = i; j >= 0; j--){
                curWidth += books[j][0];
                maxHeight = Math.max(maxHeight, books[j][1]);
                if(curWidth > shelfWidth){
                    //再多往新建一层移动一本书，新建一层放不下了。遍历结束
                    break;
                }
                //新建的一层能放下移动的书
                minHeight[i + 1] = Math.min(minHeight[i + 1], minHeight[j] + maxHeight);
            }
        }
        return minHeight[books.length];
    }

    public static void main(String[] args) {

    }


}
