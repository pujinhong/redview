package com.red.view.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.red.view.entity.BentoFileEntity;
import com.red.view.mapper.BentoFileMapper;
import com.red.view.service.IBentoFileService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author pjh
 * @created 2024/8/27
 */
@Service
public class BentoFileServiceImpl extends ServiceImpl<BentoFileMapper, BentoFileEntity> implements IBentoFileService {
    @Override
    public Object getData(BentoFileEntity entity, Map<String, Object> prams) throws Exception {

        entity.setUrl("/file/"+entity.getFileId()+"/"+entity.getFileName());

        return entity;
    }

    @Override
    public String setCache(BentoFileEntity entity, Map<String, Object> prams) throws Exception {

        return null;
    }
}
