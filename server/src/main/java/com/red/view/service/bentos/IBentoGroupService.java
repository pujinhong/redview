package com.red.view.service.bentos;

import com.red.view.entity.BentoEntity;
import com.red.view.entity.bentos.BentoGroupEntity;
import com.red.view.service.IBentoService;

import java.util.List;

/**
 * @author pjh
 * @created 2024/7/2
 */
public interface IBentoGroupService extends IBentoService<BentoGroupEntity> {


    List<BentoEntity> getViews(BentoGroupEntity entity);

}
