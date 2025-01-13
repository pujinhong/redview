package com.red.view.service.bentos;

import com.red.view.entity.bentos.BentoSqlEntity;
import com.red.view.service.IBentoService;

import java.util.List;
import java.util.Map;

/**
 * @author pjh
 * @created 2024/7/2
 */
public interface IBentoSqlService extends IBentoService<BentoSqlEntity> {

    List<Map<String, Object>> getData(BentoSqlEntity view, Map<String, Object> params);

}
