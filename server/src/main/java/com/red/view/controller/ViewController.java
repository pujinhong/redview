package com.red.view.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.red.view.entity.*;
import com.red.view.service.*;
import com.red.view.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/view")
public class ViewController {

    @Autowired
    IViewService viewService;

    @CrossOrigin
    @GetMapping("/{name}")
    public AjaxResult oneByName(@PathVariable("name") String name, @RequestParam Map<String, Object> params) throws Exception {
        BentoEntity bentoByName = viewService.getBentoByName(name);
        if(bentoByName != null){
            Object data = viewService.getDataByBento(bentoByName, params);
            return AjaxResult.ok().setData(data);
        }else {
            return AjaxResult.error("未知视图");
        }
    }

    @CrossOrigin
    @GetMapping("/{name}/tree")
    public AjaxResult treeByName(@PathVariable("name") String name, @RequestParam String field_name_id,@RequestParam String field_name_pid, @RequestParam Map<String, Object> params) throws Exception {

        BentoEntity bentoByName = viewService.getBentoByName(name);
        if(bentoByName != null){
            Object data = viewService.getDataByBento(bentoByName, params);
            if(data instanceof List) {
                List<JSONObject> list = (List<JSONObject>) data;
                // id是json对象中的id字段名，pid是json对象中的上级id字段名，将list转为树形结构
                List<JSONObject> roots = JSONUtil.convertListToTree(list, field_name_id, field_name_pid);
                return AjaxResult.ok().setData(roots);
            }
            return AjaxResult.ok().setData(data);
        }else {
            return AjaxResult.error("未知视图");
        }


    }


}
