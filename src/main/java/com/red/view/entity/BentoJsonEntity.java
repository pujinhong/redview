package com.red.view.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author pjh
 * @created 2024/7/2
 */
@Data
@TableName("t_bento_json")
public class BentoJsonEntity {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    String name;
    String title;
    String json;

}
