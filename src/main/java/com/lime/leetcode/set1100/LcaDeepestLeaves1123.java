package com.lime.leetcode.set1100;

import javafx.util.Pair;

/**
 * Created by YuHang on 2023/9/6.
 */
public class LcaDeepestLeaves1123 {


    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dp(root).getKey();
    }

    Pair<TreeNode, Integer> dp(TreeNode root){
        if(root == null){
            return new Pair<>(null, 0);
        }
        Pair<TreeNode, Integer> left = dp(root.left);
        Pair<TreeNode, Integer> right = dp(root.right);
        if(left.getValue() == right.getValue()){
            return new Pair<>(root, left.getValue() + 1);
        }else if(left.getValue() > right.getValue()){
            return new Pair<>(left.getKey(), left.getValue() + 1);
        }else {
            return new Pair<>(right.getKey(), right.getValue() + 1);
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
     }
}
