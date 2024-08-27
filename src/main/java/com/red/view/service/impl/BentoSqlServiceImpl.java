package com.red.view.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.red.view.entity.BentoSqlEntity;
import com.red.view.mapper.BentoSqlMapper;
import com.red.view.service.IBentoSqlService;
import com.red.view.service.IViewJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pjh
 * @created 2024/7/2
 */
@Service
public class BentoSqlServiceImpl extends ServiceImpl<BentoSqlMapper, BentoSqlEntity> implements IBentoSqlService {

    @Autowired
    IViewJDBC dynamicJDBC;
    @Override
    public List<Map<String, Object>> getData(BentoSqlEntity view, Map<String, Object> params) {
//        Map<String,Object> jdbcParams = new HashMap<>();
//        for(Map.Entry<String,Object> entry : params.entrySet()){
//            if(entry.getValue() ==null || entry.getKey() == null )continue;
//            if(entry.getKey().endsWith("_like_right")){
//                jdbcParams.put(entry.getKey().replace("_like_right", ""), entry.getValue().toString()+"%");
//            }else if(entry.getKey().endsWith("_like_left")){
//                jdbcParams.put(entry.getKey().replace("_like_left", ""), "%"+entry.getValue().toString());
//            }else if(entry.getKey().endsWith("_like_all")){
//                jdbcParams.put(entry.getKey().replace("_like_all", ""), "%"+entry.getValue().toString()+"%");
//            }else {
//                jdbcParams.put(entry.getKey(), entry.getValue());
//            }
//        }
        List<Map<String, Object>> data = dynamicJDBC.findListByMap(view.getDataSource(), view.getStatement(), params);

        return data;
    }

    @Override
    public String setCache(BentoSqlEntity entity, Map<String, Object> prams) throws Exception {
        return null;
    }
}
