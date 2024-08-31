package com.red.view.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.red.view.entity.AjaxResult;
import com.red.view.entity.DataSourceEntity;
import com.red.view.service.IDataSourceService;
import com.red.view.service.IViewJDBC;
import com.red.view.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public AjaxResult insert(@RequestBody DataSourceEntity entity ){
        Date now = new Date();
        entity.setCode(entity.getDbType()+"_"+ DateUtil.parseYYYYMMDDHHMMSS(now));
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        return dataSourceService.saveDataSource(entity);
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteOne(@PathVariable Integer id){
        return dataSourceService.deleteDataSource(id);
    }

    @PutMapping
    public AjaxResult update(@RequestBody DataSourceEntity entity){
        Date now = new Date();
        entity.setUpdateTime(now);
        return dataSourceService.editDataSource(entity);
    }

}
