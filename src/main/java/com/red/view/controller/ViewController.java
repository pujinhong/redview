package com.red.view.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.red.view.entity.AjaxResult;
import com.red.view.entity.BentoJsonEntity;
import com.red.view.entity.BentoSqlEntity;
import com.red.view.service.IBentoJsonService;
import com.red.view.service.IBentoSqlService;
import com.red.view.service.IViewJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/view")
public class ViewController {
    @Autowired
    IViewJDBC dynamicJDBC;
    @Autowired
    IBentoSqlService bentoSqlService;
    @Autowired
    IBentoJsonService bentoJsonService;

    @CrossOrigin
    @GetMapping("/{name}")
    public AjaxResult oneByName(@PathVariable("name") String name, @RequestParam Map<String, Object> params) throws IOException {
        // SQL视图
        BentoSqlEntity sqlView = bentoSqlService.getOne(new LambdaQueryWrapper<BentoSqlEntity>().eq(BentoSqlEntity::getName, name));
        if(sqlView != null){
            return AjaxResult.ok().setData(sqlView);
        }
        // JSON视图
        BentoJsonEntity jsonView = bentoJsonService.getOne(new LambdaQueryWrapper<BentoJsonEntity>().eq(BentoJsonEntity::getName, name));
        if(jsonView != null){
            return AjaxResult.ok().setData(jsonView);
        }
        // 组合视图

        // 订阅视图

        // 文件视图

        // 流媒体视图

        return AjaxResult.error("未知视图");
    }

    @CrossOrigin
    @GetMapping("/{name}/goview/{type}")
    public AjaxResult goViewByName(@PathVariable("name") String name,@PathVariable("type") String type, @RequestParam Map<String, Object> params) throws IOException {

        AjaxResult ajaxResult = oneByName(name, params);

        //TODO 转为goview可用的结构
        ajaxResult = ajaxResult;

        return  ajaxResult;

    }


}
