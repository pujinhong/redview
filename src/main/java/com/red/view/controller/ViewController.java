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
    @GetMapping("/sql/{name}")
    public AjaxResult oneByName(@PathVariable("name") String name, @RequestParam Map<String, Object> params) throws IOException {
        BentoSqlEntity one = bentoSqlService.getOne(new LambdaQueryWrapper<BentoSqlEntity>().eq(BentoSqlEntity::getName, name));
        if(one == null){
            return AjaxResult.error("未找到对应的块");
        }

        List<Map<String, Object>> data = dynamicJDBC.findListByMap(one.getDataSource(), one.getStatement(), params);
        return AjaxResult.ok().setData(data);
    }

    @CrossOrigin
    @GetMapping("/json/{name}")
    public AjaxResult oneByName(@PathVariable("name") String name) throws IOException {
        BentoJsonEntity one = bentoJsonService.getOne(new LambdaQueryWrapper<BentoJsonEntity>().eq(BentoJsonEntity::getName, name));
        if(one == null){
            return AjaxResult.error("未找到对应的块");
        }
        JSONObject json = JSON.parseObject(one.getJson());
        return AjaxResult.ok().setData(json);
    }
}
