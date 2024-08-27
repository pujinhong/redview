package com.red.view.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.red.view.entity.AjaxResult;
import com.red.view.entity.BentoJsonEntity;
import com.red.view.mapper.BentoJsonMapper;
import com.red.view.service.IBentoJsonService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author pjh
 * @created 2024/7/2
 */
@Service
public class BentoJsonServiceImpl extends ServiceImpl<BentoJsonMapper, BentoJsonEntity> implements IBentoJsonService {

    @Override
    public Object getData(BentoJsonEntity view, Map<String, Object> params) throws Exception {
        String strJson = view.getJson();
        try {
            if (strJson.trim().startsWith("[")) {
                return JSON.parseArray(strJson);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(strJson);
                return jsonObject;
            }
        }catch (Exception ex){
            throw new Exception("JSON格式错误");
        }
    }

    @Override
    public String setCache(BentoJsonEntity entity, Map<String, Object> prams) throws Exception {
        return null;
    }
}
