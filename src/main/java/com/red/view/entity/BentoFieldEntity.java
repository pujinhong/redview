package com.red.view.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_bento_field")
public class BentoFieldEntity {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    String fieldName;   //字段名
    String bentoId;     //视图ID
    String aliasName;       //字段别名
    Integer index;      //字段排序

}
