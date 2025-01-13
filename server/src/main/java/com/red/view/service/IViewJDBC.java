package com.red.view.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IViewJDBC {
    int update(final String dbKey, String sql, Object... param);
    int updateByMap(final String dbKey, String sql, Map<String, Object> data) throws SQLException;
    Map<String, Object> findOne(final String dbKey, String sql, Object... param);
    Map<String, Object> findOneByMap(final String dbKey, String sql, Map<String, Object> data);
    <T> Object findOne(final String dbKey, String sql, Class<T> clazz, Object... param);
    <T> Object findOneByMap(final String dbKey, String sql, Class<T> clazz, Map<String, Object> data);
    List<Map<String, Object>> findList(final String dbKey, String sql, Object... param);
    Map<String, Object> queryCount(String dbKey, String sql, Map<String, Object> param);
    List<Map<String, Object>> findListByNamedParam(final String dbKey, String sql, Map<String, Object> param);
    List<Map<String, Object>> findListByMap(final String dbKey, String sql, Map<String, Object> data);
    <T> List<T> findList(final String dbKey, String sql, Class<T> clazz, Object... param);
    <T> List<T> findListByMap(final String dbKey, String sql, Class<T> clazz, Map<String, Object> data);
    <T> List<T> findListEntities(final String dbKey, String sql, Class<T> clazz, Object... param);
    <T> List<T> findListEntitiesByMap(final String dbKey, String sql, Class<T> clazz, Map<String, Object> data);

}
