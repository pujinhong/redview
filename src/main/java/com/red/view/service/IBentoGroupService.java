package com.red.view.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.red.view.entity.BentoEntity;
import com.red.view.entity.BentoGroupEntity;

import java.util.List;
import java.util.Map;

/**
 * @author pjh
 * @created 2024/7/2
 */
public interface IBentoGroupService extends IBentoService<BentoGroupEntity> {


    List<BentoEntity> getViews(BentoGroupEntity entity);

}
