package com.red.view.util;

import java.util.UUID;

/**
 * @author pjh
 * @created 2024/8/27
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
