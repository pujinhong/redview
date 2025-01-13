package com.red.view.entity.bentos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.red.view.entity.BentoEntity;
import lombok.Data;

/**
 * 文件视图
 * @author pjh
 * @created 2024/8/27
 */
@Data
@TableName("t_bento_file")
public class BentoFileEntity extends BentoEntity {
    public  String fileId; //文件id uuid 32位 文件夹名称
    public  String fileName; //文件名称（包含扩展名）
    public  String fileType; // 文件类型
    @Override
    public String getType() {
        return "FILE";
    }

    // 文件发布路径
    @TableField(exist = false)
    String url;
}
