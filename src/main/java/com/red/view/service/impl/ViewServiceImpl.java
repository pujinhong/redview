package com.red.view.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.red.view.entity.*;
import com.red.view.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author pjh
 * @created 2024/8/27
 */
@Service
public class ViewServiceImpl implements IViewService {
    @Autowired
    IViewJDBC dynamicJDBC;
    @Autowired
    IBentoSqlService bentoSqlService;
    @Autowired
    IBentoJsonService bentoJsonService;

    @Autowired
    IBentoGroupService bentoGroupService;

    @Autowired
    IBentoFileService bentoFileService;
    @Override
    public BentoEntity getBentoByName(String name) throws Exception {

        // SQL视图
        BentoSqlEntity sqlView = bentoSqlService.getOne(new LambdaQueryWrapper<BentoSqlEntity>().eq(BentoSqlEntity::getName, name));
        if(sqlView!= null){
            return sqlView;
        }
        // JSON视图
        BentoJsonEntity jsonView = bentoJsonService.getOne(new LambdaQueryWrapper<BentoJsonEntity>().eq(BentoJsonEntity::getName, name));
        if(jsonView!= null){
            return jsonView;
        }
        // 组合视图
        BentoGroupEntity groupView = bentoGroupService.getOne(new LambdaQueryWrapper<BentoGroupEntity>().eq(BentoGroupEntity::getName, name));
        if(groupView!= null){
            return groupView;
        }

        // 文件视图
        BentoFileEntity fileView = bentoFileService.getOne(new LambdaQueryWrapper<BentoFileEntity>().eq(BentoFileEntity::getName, name));
        if(fileView!= null){
            return fileView;
        }

        // 订阅视图

        // 流媒体视图

        return null;
    }

    @Override
    public Object getDataByBento(BentoEntity view, Map<String, Object> prams) throws Exception {
        if(view instanceof BentoSqlEntity){
            return bentoSqlService.getData((BentoSqlEntity) view,prams);
        }else if(view instanceof BentoJsonEntity){
            return bentoJsonService.getData((BentoJsonEntity) view,prams);
        }else if(view instanceof BentoGroupEntity){
            return bentoGroupService.getData((BentoGroupEntity) view,prams);
        }else if(view instanceof BentoFileEntity){
            return bentoFileService.getData((BentoFileEntity) view,prams);
        }else {
            return null;
        }
    }
}
