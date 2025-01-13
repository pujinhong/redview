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
@TableName("t_bento_sql")
public class BentoSqlEntity extends BentoEntity {

    String dataSource;
    String statement;

    @Override
    public String getType() {
        return "SQL";
    }
}
