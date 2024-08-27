package com.red.view.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@TableName("t_bento_field")
public class BentoFieldEntity{

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    String fieldName;   //字段名
    String bentoId;     //视图ID
    String aliasName;       //字段别名
    Integer index;      //字段排序
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;

    /**
     * 更新日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;


}
