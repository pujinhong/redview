package com.red.view.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.red.view.service.IDataSourceService;
import com.red.view.service.IViewJDBC;
import com.red.view.util.DateUtil;
import com.red.view.util.ObjectHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Spring JDBC 实时数据库访问
 *
 */
@Slf4j
@Component
public class ViewJDBC implements IViewJDBC {

    @Autowired
    IDataSourceService dbPool;



    /**
     * 关闭数据库连接池
     *
     * @param dbKey
     * @return
     */
    public void closeDbKey(final String dbKey) {
        DruidDataSource dataSource = dbPool.getDataSource(dbKey);
        try {
            if (dataSource != null && !dataSource.isClosed()) {
                dataSource.getConnection().commit();
                dataSource.getConnection().close();
                dataSource.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private JdbcTemplate getJdbcTemplate(String dbKey) {
        DruidDataSource dataSource = dbPool.getDataSource(dbKey);
        return new JdbcTemplate(dataSource);
    }

    /**
     * 根据数据源获取NamedParameterJdbcTemplate
     * @param dbKey
     * @return
     */
    private NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String dbKey) {
        DruidDataSource dataSource = dbPool.getDataSource(dbKey);
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int update(final String dbKey, String sql, Object... param) {
        int effectCount;
        JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
        if (param == null || param.length == 0) {
            effectCount = jdbcTemplate.update(sql);
        } else {
            effectCount = jdbcTemplate.update(sql, param);
        }
        return effectCount;
    }

    /**
     * 符合NamedParameterJdbc的模板规则（ 占位符格式为【:arg】 参数集合为【map<arg,value>】）的Update
     *
     * @param dbKey 数据源标识
     * @param sql   执行sql语句，sql符合NamedParameterJdbc的模板规则
     * @param data  sql语法中需要判断的数据及sql拼接注入中需要的数据
     * @return
     */
    @Override
    public int updateByMap(final String dbKey, String sql, Map<String, Object> data) throws SQLException  {
        int effectCount;
        JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        MapSqlParameterSource msps = buildParameters(data);
        effectCount = namedParameterJdbcTemplate.update(sql, msps);
        return effectCount;
    }
    @Override
    public Map<String, Object> findOne(final String dbKey, String sql, Object... param) {
        List<Map<String, Object>> list;
        list = findList(dbKey, sql, param);
        if (list.isEmpty()) {
            log.error("Except one, but not find actually");
            return null;
        }
        if (list.size() > 1) {
            log.error("Except one, but more than one actually");
        }
        return list.get(0);
    }

    /**
     * 符合NamedParameterJdbc的模板规则（ 占位符格式为【:arg】 参数集合为【map<arg,value>】）的查询 返回Map
     *
     * @param dbKey 数据源标识
     * @param sql   执行sql语句，sql符合NamedParameterJdbc的模板规则
     * @param data  sql语法中需要判断的数据及sql拼接注入中需要的数据
     * @return
     */
    @Override
    public Map<String, Object> findOneByMap(final String dbKey, String sql, Map<String, Object> data) {
        List<Map<String, Object>> list;
        list = findListByMap(dbKey, sql, data);
        if (list.isEmpty()) {
            log.error("Except one, but not find actually");
        }
        if (list.size() > 1) {
            log.error("Except one, but more than one actually");
        }
        return list.get(0);
    }

    /**
     * 直接sql查询 根据clazz返回单个实例
     *
     * @param dbKey 数据源标识
     * @param sql   执行sql语句
     * @param clazz 返回实例的Class
     * @param param
     * @return
     */
    @Override
    public <T> Object findOne(final String dbKey, String sql, Class<T> clazz, Object... param) {
        Map<String, Object> map = findOne(dbKey, sql, param);
        return ObjectHelper.setAll(clazz, map);
    }

    /**
     * 符合NamedParameterJdbc的模板规则（ 占位符格式为【:arg】 参数集合为【map<arg,value>】）的查询 返回单个实例
     *
     * @param dbKey 数据源标识
     * @param sql   执行sql语句，sql符合NamedParameterJdbc的模板规则
     * @param clazz 返回实例的Class
     * @param data  sql语法中需要判断的数据及sql拼接注入中需要的数据
     * @return
     */
    @Override
    public <T> Object findOneByMap(final String dbKey, String sql, Class<T> clazz, Map<String, Object> data) {
        Map<String, Object> map = findOneByMap(dbKey, sql, data);
        return ObjectHelper.setAll(clazz, map);
    }
    @Override
    public List<Map<String, Object>> findList(final String dbKey, String sql, Object... param) {
        List<Map<String, Object>> list;
        JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

        if (param == null || param.length == 0) {
            list = jdbcTemplate.queryForList(sql);
        } else {
            list = jdbcTemplate.queryForList(sql, param);
        }
        return list;
    }

    /**
     * 查询数量
     * @param dbKey
     * @param sql
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryCount(String dbKey, String sql, Map<String, Object> param){
        NamedParameterJdbcTemplate npJdbcTemplate = getNamedParameterJdbcTemplate(dbKey);
        return npJdbcTemplate.queryForMap(sql, param);
    }

    /**
     * 查询列表数据
     * @param dbKey
     * @param sql
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> findListByNamedParam(final String dbKey, String sql, Map<String, Object> param) {
        NamedParameterJdbcTemplate npJdbcTemplate = getNamedParameterJdbcTemplate(dbKey);
        List<Map<String, Object>> list = npJdbcTemplate.queryForList(sql, param);
        return list;
    }

    /**
     * 符合NamedParameterJdbc的模板规则（ 占位符格式为【:arg】 参数集合为【map<arg,value>】）的查询
     *
     * @param dbKey 数据源标识
     * @param sql   执行sql语句，sql符合NamedParameterJdbc的模板规则
     * @param data  sql语法中需要判断的数据及sql拼接注入中需要的数据
     * @return
     */
    @Override
    public List<Map<String, Object>> findListByMap(final String dbKey, String sql, Map<String, Object> data) {
        List<Map<String, Object>> list;
        JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
        MapSqlParameterSource parameterSource = buildParameters(data);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        list = namedParameterJdbcTemplate.queryForList(sql, parameterSource);
        return list;
    }

    /**
     * 此方法只能返回单列，不能返回实体类
     * @param dbKey 数据源的key
     * @param sql sal
     * @param clazz 类
     * @param param 参数
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> findList(final String dbKey, String sql, Class<T> clazz, Object... param) {
        List<T> list;
        JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);

        if (param == null || param.length == 0) {
            list = jdbcTemplate.queryForList(sql, clazz);
        } else {
            list = jdbcTemplate.queryForList(sql, clazz, param);
        }
        return list;
    }

    /**
     * 符合NamedParameterJdbc的模板规则（ 占位符格式为【:arg】 参数集合为【map<arg,value>】）的查询 返回单列数据list
     *
     * @param dbKey 数据源标识
     * @param sql   执行sql语句，符合NamedParameterJdbc的模板规则
     * @param clazz 类型Long、String等
     * @param data  sql语法中需要判断的数据及sql拼接注入中需要的数据
     * @return
     */
    @Override
    public <T> List<T> findListByMap(final String dbKey, String sql, Class<T> clazz, Map<String, Object> data) {
        List<T> list;
        JdbcTemplate jdbcTemplate = getJdbcTemplate(dbKey);
        MapSqlParameterSource parameterSource = buildParameters(data);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        list = namedParameterJdbcTemplate.queryForList(sql, parameterSource, clazz);
        return list;
    }

    /**
     * 符合NamedParameterJdbc的模板规则（ 占位符格式为【:arg】 参数集合为【map<arg,value>】）的直接sql查询 返回实体类列表
     *
     * @param dbKey 数据源标识
     * @param sql   执行sql语句，符合NamedParameterJdbc的模板规则
     * @param clazz 返回实体类列表的class
     * @param param sql拼接注入中需要的数据
     * @return
     */
    @Override
    public <T> List<T> findListEntities(final String dbKey, String sql, Class<T> clazz, Object... param) {
        List<Map<String, Object>> queryList = findList(dbKey, sql, param);
        return ObjectHelper.transList2Entrys(queryList, clazz);
    }

    /**
     * 符合NamedParameterJdbc的模板规则（ 占位符格式为【:arg】 参数集合为【map<arg,value>】）的查询 返回实体类列表
     *
     * @param dbKey 数据源标识
     * @param sql   执行sql语句，sql符合NamedParameterJdbc的模板规则
     * @param clazz 返回实体类列表的class
     * @param data  sql语法中需要判断的数据及sql拼接注入中需要的数据
     * @return
     */
    @Override
    public <T> List<T> findListEntitiesByMap(final String dbKey, String sql, Class<T> clazz, Map<String, Object> data) {
        List<Map<String, Object>> queryList = findListByMap(dbKey, sql, data);
        return ObjectHelper.transList2Entrys(queryList, clazz);
    }


    private MapSqlParameterSource buildParameters(Map<String, Object> columnValues) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
            if (entry.getValue() instanceof String && DateUtil.isDateString((String) entry.getValue())) {
                // 如果值是时间字符串，则转换为时间对象
                parameters.addValue(entry.getKey(), DateUtil.parseDateString((String) entry.getValue()));
            } else {
                parameters.addValue(entry.getKey(), entry.getValue());
            }
        }
        return parameters;
    }


}
