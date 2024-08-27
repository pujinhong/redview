package com.red.view.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.red.view.entity.*;
import com.red.view.service.*;
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
    @GetMapping("/{name}/goview/{type}")
    public AjaxResult goViewByName(@PathVariable("name") String name,@PathVariable("type") String type, @RequestParam Map<String, Object> params) throws Exception {

        AjaxResult ajaxResult = oneByName(name, params);

        //TODO 转为goview可用的结构
        ajaxResult = ajaxResult;

        return  ajaxResult;

    }


}
