package com.red.view.service;

import com.red.view.entity.BentoEntity;

import java.util.Map;

/**
 * @author pjh
 * @created 2024/8/27
 */
public interface IViewService {

    BentoEntity getBentoByName(String name) throws Exception;

    Object getDataByBento(BentoEntity view, Map<String, Object> prams) throws Exception;
}
