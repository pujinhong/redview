package com.red.view.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 组合视图
 * @author pjh
 * @created 2024/8/26
 */
@Data
@TableName("t_bento_group")
public class BentoGroupEntity  extends BentoEntity{

    String statement;

    @Override
    String getType() {
        return "GROUP";
    }
}
