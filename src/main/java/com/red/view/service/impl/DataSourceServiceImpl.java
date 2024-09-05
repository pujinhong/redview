package com.red.view.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.red.view.entity.AjaxResult;
import com.red.view.entity.DataSourceEntity;
import com.red.view.mapper.DataSourceMapper;
import com.red.view.service.IDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSourceEntity> implements IDataSourceService {

    @Autowired
    private DruidDataSourceCreator dataSourceCreator;


    @Autowired
    private DataSource dataSource;

    /** 数据源连接池缓存【本地 class缓存 - 不支持分布式】 */
    private static Map<String, DruidDataSource> dbSources = new HashMap<>();

    @Override
    public AjaxResult saveDataSource(DataSourceEntity sysDataSource) {
        try {
            long count = checkDbCode(sysDataSource.getCode());
            if (count > 0) {
                return AjaxResult.error("数据源编码已存在");
            }
            String dbPassword = sysDataSource.getDbPassword();

            //TODO 加密数据库密码


            boolean result = save(sysDataSource);
            if (result) {
                //动态创建数据源
                //addDynamicDataSource(sysDataSource, dbPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.ok("添加成功！");
    }

    @Override
    public AjaxResult editDataSource(DataSourceEntity sysDataSource) {
        try {
            DataSourceEntity d = getById(sysDataSource.getId());
            removeCache(d.getCode());

            //TODO 加密数据库密码

            Boolean result=updateById(sysDataSource);
            if(result){
                //先删除老的数据源
               // removeDynamicDataSource(d.getCode());
                //添加新的数据源
                //addDynamicDataSource(sysDataSource,dbPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.ok("编辑成功!");
    }

    @Override
    public AjaxResult deleteDataSource(Integer id) {
        DataSourceEntity sysDataSource = getById(id);
        removeCache(sysDataSource.getCode());
        removeById(id);
        return AjaxResult.ok("删除成功!");
    }

    /**
     * 动态添加数据源 【注册mybatis动态数据源】
     *
     * @param sysDataSource 添加数据源数据对象
     * @param dbPassword    未加密的密码
     */
    @Override
    public void addDynamicDataSource(DataSourceEntity sysDataSource, String dbPassword) {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl(sysDataSource.getDbUrl());
        dataSourceProperty.setPassword(dbPassword);
        dataSourceProperty.setDriverClassName(sysDataSource.getDbDriver());
        dataSourceProperty.setUsername(sysDataSource.getDbUsername());
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        try {
            ds.addDataSource(sysDataSource.getCode(), dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据源
     * @param code
     */
    @Override
    public void removeDynamicDataSource(String code) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.removeDataSource(code);
    }

    /**
     * 检查数据源编码是否存在
     *
     * @param dbCode
     * @return
     */
    @Override
    public long checkDbCode(String dbCode) {
        QueryWrapper<DataSourceEntity> qw = new QueryWrapper();
        qw.lambda().eq(true, DataSourceEntity::getCode, dbCode);
        return count(qw);
    }

    /**
     * 获取数据源【禁止更改】
     *
     * @param dbSource
     * @return
     */

    public DruidDataSource createJdbcDataSource(final DataSourceEntity dbSource) {
        DruidDataSource dataSource = new DruidDataSource();

        String driverClassName = dbSource.getDbDriver();
        String url = dbSource.getDbUrl();
        String dbUser = dbSource.getDbUsername();
        String dbPassword = dbSource.getDbPassword();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        //TODO 根据数据库不同类型确定校验语句
        //dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setBreakAfterAcquireFailure(true);
        dataSource.setConnectionErrorRetryAttempts(0);
        dataSource.setUsername(dbUser);
        dataSource.setMaxWait(30000);
        dataSource.setKillWhenSocketReadTimeout(false);
        dataSource.setSocketTimeout(1000*60*10); //sql执行10分钟超时
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
    /**
     * 通过 dbKey ,获取数据源
     *
     * @param dbKey
     * @return
     */
    @Override
    public DruidDataSource getDataSource(String dbKey) {
        DruidDataSource dds = dbSources.get(dbKey);
        if (dds != null && !dds.isClosed()) {
            return dds;
        }else {
            //获取多数据源配置
            DataSourceEntity config = getOne(new LambdaQueryWrapper<DataSourceEntity>().eq(DataSourceEntity::getCode, dbKey));
            DruidDataSource dataSource = createJdbcDataSource(config);
            if(dataSource.isEnable()) {
                putDataSource(dbKey, dataSource);
            }else {
                new Exception("动态数据源连接失败，dbKey："+dbKey).printStackTrace();
            }
            return dataSource;
        }
    }

    /**
     * put 数据源缓存
     *
     * @param dbKey
     * @param db
     */
    @Override
    public void putDataSource(String dbKey, DruidDataSource db) {
        dbSources.put(dbKey, db);
    }

    /**
     * 清空数据源缓存
     */
    @Override
    public void cleanAllCache() {
        //关闭数据源连接
        for(Map.Entry<String, DruidDataSource> entry : dbSources.entrySet()){
            String dbkey = entry.getKey();
            DruidDataSource druidDataSource = entry.getValue();
            if(druidDataSource!=null && druidDataSource.isEnable()){
                druidDataSource.close();
            }
        }
        //清空缓存
        dbSources.clear();
    }
    @Override
    public void removeCache(String dbKey) {
        //关闭数据源连接
        DruidDataSource druidDataSource = dbSources.get(dbKey);
        if(druidDataSource!=null && druidDataSource.isEnable()){
            druidDataSource.close();
        }
        //清空缓存
        dbSources.remove(dbKey);
    }

}
