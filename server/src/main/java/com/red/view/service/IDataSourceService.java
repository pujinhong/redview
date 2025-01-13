package com.red.view.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.red.view.entity.AjaxResult;
import com.red.view.entity.DataSourceEntity;

public interface IDataSourceService extends IService<DataSourceEntity> {

    /**
     * 添加数据源
     * @param sysDataSource
     * @return
     */
    AjaxResult saveDataSource(DataSourceEntity sysDataSource);

    /**
     * 修改数据源
     * @param sysDataSource
     * @return
     */
    AjaxResult editDataSource(DataSourceEntity sysDataSource);


    /**
     * 删除数据源
     * @param id
     * @return
     */
    AjaxResult deleteDataSource(Integer id);

    void addDynamicDataSource(DataSourceEntity sysDataSource, String dbPassword);

    void removeDynamicDataSource(String code);

    long checkDbCode(String dbCode);

    DruidDataSource getDataSource(String dbKey);

    void putDataSource(String dbKey, DruidDataSource db);

    void cleanAllCache();

    void removeCache(String dbKey);
}
