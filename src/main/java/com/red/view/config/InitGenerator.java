package com.red.view.config;

/**
 * @author pjh
 * @created 2024/7/22
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class InitGenerator implements ApplicationRunner {


    @Value("${spring.file.local.path}")
    private String outputPath;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //创建生成目录
        File fileOutPath = new File(outputPath);
        if (!fileOutPath.exists()) {
            fileOutPath.mkdirs();
        }

    }
}
