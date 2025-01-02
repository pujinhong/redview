package com.red.view.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pjh
 * @created 2025/1/2
 */
public class JSONUtil {
    // 递归函数，将列表转换为树形结构
    public static List<JSONObject> convertListToTree(List<JSONObject> list, String idField, String pidField) {
        Map<String, List<JSONObject>> treeMap = new HashMap<>();
        List<JSONObject> rootNodes = new ArrayList<>();

        // 遍历列表，将每个节点放入以其pid为键的映射中
        for (JSONObject node : list) {
            String pid = node.getString(pidField);
            if (treeMap.containsKey(pid)) {
                treeMap.get(pid).add(node);
            } else {
                List<JSONObject> newList = new ArrayList<>();
                newList.add(node);
                treeMap.put(pid, newList);
            }
        }

        // 找到根节点并添加到根节点列表中
        for (JSONObject node : list) {
            String id = node.getString(idField);
            if (!treeMap.containsKey(id)) {
                rootNodes.add(node);
            }
        }

        // 递归构建子树
        for (JSONObject rootNode : rootNodes) {
            rootNode.put("children", buildChildren(treeMap, rootNode.getString(idField)));
        }

        return rootNodes;
    }

    // 递归构建子树
    private static List<JSONObject> buildChildren(Map<String, List<JSONObject>> treeMap, String id) {
        List<JSONObject> children = treeMap.get(id);
        if (children == null) {
            return new ArrayList<>();
        }
        for (JSONObject child : children) {
            child.put("children", buildChildren(treeMap, child.getString("id")));
        }
        return children;
    }
}
