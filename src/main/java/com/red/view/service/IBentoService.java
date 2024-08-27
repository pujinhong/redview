package com.red.view.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.red.view.entity.BentoEntity;

import java.util.Map;

public interface IBentoService<T> extends IService<T> {

    Object getData(T entity , Map<String,Object> prams) throws Exception;

    String setCache(T entity, Map<String,Object> prams) throws Exception;
}
