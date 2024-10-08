package com.red.view.entity.bentos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.red.view.entity.BentoEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author pjh
 * @created 2024/7/2
 */
@Data
@TableName("t_bento_json")
public class BentoJsonEntity  extends BentoEntity {


    String json;


    @Override
    public String getType() {
        return "JSON";
    }
}
