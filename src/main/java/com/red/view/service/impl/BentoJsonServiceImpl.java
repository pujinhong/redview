package com.red.view.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.red.view.entity.BentoJsonEntity;
import com.red.view.mapper.BentoJsonMapper;
import com.red.view.service.IBentoJsonService;
import org.springframework.stereotype.Service;

/**
 * @author pjh
 * @created 2024/7/2
 */
@Service
public class BentoJsonServiceImpl extends ServiceImpl<BentoJsonMapper, BentoJsonEntity> implements IBentoJsonService {
}
