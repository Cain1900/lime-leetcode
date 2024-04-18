package com.lime.leetcode.set300;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuHang on 2023/9/18.
 */
public class Rob337 {

    Map<TreeNode, Integer> selectMap = new HashMap<>();
    Map<TreeNode, Integer> dropMap = new HashMap<>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(selectMap.getOrDefault(root, 0), dropMap.getOrDefault(root, 0));
    }

    private void dfs(TreeNode root) {
        if(root == null){
            return;
        }
        dfs(root.left);
        dfs(root.right);
        selectMap.put(root, root.val + dropMap.getOrDefault(root.left, 0) + dropMap.getOrDefault(root.right, 0));
        dropMap.put(root, Math.max(selectMap.getOrDefault(root.left, 0), dropMap.getOrDefault(root.left, 0)) + Math.max(selectMap.getOrDefault(root.right, 0), dropMap.getOrDefault(root.right, 0)));
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(){}

        TreeNode(int val){
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
