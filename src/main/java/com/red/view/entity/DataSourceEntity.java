package com.red.view.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 多数据源实体类
 */
@Data
@TableName("t_datasource")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DataSourceEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 数据源编码
     */
    private String code;
    /**
     * 数据源名称
     */
    private String name;
    /**
     * 描述
     */
    private String remark;
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * 驱动类
     */
    private String dbDriver;
    /**
     * 数据源地址
     */
    private String dbUrl;
    /**
     * 数据库名称
     */
    private String dbInstance;
    /**
     * 用户名
     */
    private String dbUsername;
    /**
     * 密码
     */
    private String dbPassword;
    /**
     * 端口
     */
    private Integer dbPort;
    /**
     * IP地址
     */
    private String dbHost;

    /**
     * 启用状态
     */
    private Integer online;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
    /**
     * 连接状态
     */
    @TableField(exist = false)
    private Integer status;

}
