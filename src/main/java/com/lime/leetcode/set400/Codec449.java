package com.lime.leetcode.set400;


/**
 * Created by YuHang on 2023/9/4.
 */
public class Codec449 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "\"\"";
        }
        return new StringBuilder().append("{\"val\":").append(root.val)
                .append(", \"left\":").append(serialize(root.left))
                .append(", \"right\":").append(serialize(root.right))
                .append("}").toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return new TreeNode(1);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }
}
