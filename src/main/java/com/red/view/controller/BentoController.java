package com.red.view.controller;

import com.red.view.entity.AjaxResult;
import com.red.view.entity.BentoJsonEntity;
import com.red.view.entity.BentoSqlEntity;
import com.red.view.service.IBentoJsonService;
import com.red.view.service.IBentoSqlService;
import com.red.view.service.IViewJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 更新配置
 */
@RestController
@RequestMapping("/bento")
public class BentoController {

    @Autowired
    IViewJDBC dynamicJDBC;
    @Autowired
    IBentoSqlService bentoSqlService;
    @Autowired
    IBentoJsonService bentoJsonService;

    @GetMapping("/list")
    public AjaxResult list(){
        List<Object> result = new ArrayList<>();
        List<BentoSqlEntity> listSql = bentoSqlService.list();
        result.addAll(listSql);
        List<BentoJsonEntity> listJson = bentoJsonService.list();
        result.addAll(listJson);
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
