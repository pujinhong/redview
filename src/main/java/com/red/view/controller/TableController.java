/*************************************************************
 *                                                           *
 * 当视图实在无法满足需求，并且对SQL注入无所谓的情况下，偷偷滴开放此接口 *
 *                                                           *
 *************************************************************/

package com.red.view.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.red.view.entity.AjaxResult;
import com.red.view.service.IDataSourceService;
import com.red.view.service.IViewJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author pjh
 * @created 2024/11/4
 */
@RestController
@RequestMapping("/table")
public class TableController {
    @Autowired
    IViewJDBC dynamicJDBC;
    @Autowired
    IDataSourceService dataSourceService;

    /**
     * 执行sql语句（ 不安全的接口）
     * @param datasource 数据源
     * @param sql sql语句
     * @return
     */
    @CrossOrigin
    //@GetMapping("/sql")
    AjaxResult getSqlList(String datasource, String sql){
        if(datasource == null || sql == null){
            return AjaxResult.error("参数错误");
        }
        if(sql.toLowerCase().contains("update") ||
                sql.toLowerCase().contains("delete") ||
                sql.toLowerCase().contains("insert")||
                sql.toLowerCase().contains("drop") ||
                sql.toLowerCase().contains("alter") ||
                sql.toLowerCase().contains("truncate")){
            return AjaxResult.error("不允许执行此操作,仅支持查询语句。");
        }
        DruidDataSource dataSource = dataSourceService.getDataSource(datasource);
        if(dataSource == null){
            return AjaxResult.error("数据源不存在");
        }
        try {
            List<Map<String, Object>> data = dynamicJDBC.findList(datasource, sql, null);
            return AjaxResult.ok().setData(data);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }


}
