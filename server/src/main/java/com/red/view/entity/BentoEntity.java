package com.red.view.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author pjh
 * @created 2024/8/27
 */
@Data
public abstract class BentoEntity {

    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;
    public String name;
    public String title;
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


    public abstract String getType();
}
