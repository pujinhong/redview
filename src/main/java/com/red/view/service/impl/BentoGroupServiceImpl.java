package com.red.view.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.red.view.entity.*;
import com.red.view.mapper.BentoGroupMapper;
import com.red.view.service.IBentoGroupService;
import com.red.view.service.IBentoJsonService;
import com.red.view.service.IBentoSqlService;
import com.red.view.service.IViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pjh
 * @created 2024/7/2
 */
@Service
public class BentoGroupServiceImpl extends ServiceImpl<BentoGroupMapper, BentoGroupEntity> implements IBentoGroupService {

    @Autowired
    @Lazy
    IViewService viewService;

    @Autowired
    IBentoSqlService bentoSqlService;
    @Autowired
    IBentoJsonService bentoJsonService;




    @Override
    public List<BentoEntity> getViews(BentoGroupEntity entity) {
        //识别${vw: XXX }
        List<BentoEntity> views = new ArrayList<>();
        List<String> paramNames = getParamNames(entity.getStatement());
        List<String> viewNames =  paramNames.stream().filter(name -> name.startsWith("vw:")).collect(Collectors.toList());
        for (String name : viewNames) {
            String viewName = name.substring(3).trim();
            try{
                BentoEntity bentoByName = viewService.getBentoByName(viewName);
                if(bentoByName != null){
                    views.add(bentoByName);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return views;
    }

    @Override
    public Object getData(BentoGroupEntity entity, Map<String, Object> prams) throws Exception {
        List<BentoEntity> views = getViews(entity);
        for (BentoEntity view : views) {
            String name = view.getName();


        }
        return null;
    }

    @Override
    public String setCache(BentoGroupEntity entity, Map<String, Object> prams) throws Exception {
        return null;
    }

    public List<String> getParamNames(String statement){
        List<String> paramNames = new ArrayList<>();
        int start = statement.indexOf("${");
        while (start >= 0) {
            int end = statement.indexOf('}', start);
            if (end > start) {
                paramNames.add(statement.substring(start, end + 1));
            }
            start = statement.indexOf("${", end);
        }
        return paramNames;
    }

}
