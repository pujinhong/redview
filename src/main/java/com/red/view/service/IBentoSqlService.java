package com.red.view.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.red.view.entity.BentoSqlEntity;

import java.util.List;
import java.util.Map;

/**
 * @author pjh
 * @created 2024/7/2
 */
public interface IBentoSqlService extends IBentoService<BentoSqlEntity> {

    List<Map<String, Object>> getData(BentoSqlEntity view, Map<String, Object> params);

}
