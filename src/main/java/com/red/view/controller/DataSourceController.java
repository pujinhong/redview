package com.red.view.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.red.view.entity.AjaxResult;
import com.red.view.entity.BentoJsonEntity;
import com.red.view.entity.BentoSqlEntity;
import com.red.view.entity.DataSourceEntity;
import com.red.view.service.IBentoJsonService;
import com.red.view.service.IBentoSqlService;
import com.red.view.service.IDataSourceService;
import com.red.view.service.IViewJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 更新配置
 */
@RestController
@RequestMapping("/ds")
public class DataSourceController {

    @Autowired
    IViewJDBC dynamicJDBC;
    @Autowired
    IDataSourceService dataSourceService;

    @GetMapping("/list")
    public AjaxResult list(@RequestParam Map<String, Object> params){
        QueryWrapper<DataSourceEntity> wrapper = new QueryWrapper<>();
        //遍历params
        params.forEach((key, value) -> wrapper.like(key, value));
        List<DataSourceEntity> result = dataSourceService.list(wrapper);
        return AjaxResult.ok().setData(result);
    }
    @GetMapping("/{id}")
    public AjaxResult oneById(@PathVariable("id") Integer id){
        return AjaxResult.ok();
    }

    @PostMapping
    public AjaxResult insert(@RequestBody BentoSqlEntity entity ){
        return AjaxResult.ok();
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteOne(@PathVariable("id") Integer id){
        return AjaxResult.ok();
    }

    @PutMapping
    public AjaxResult update(@RequestBody BentoSqlEntity entity){
        return AjaxResult.ok();
    }

}
