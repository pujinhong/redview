package com.red.view.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 树型视图
 * @author pjh
 * @created 2024/8/26
 */
@Data
@TableName("t_bento_tree")
public class BentoTreeEntity extends BentoEntity{

    @Override
    String getType() {
        return "TREE";
    }
}
