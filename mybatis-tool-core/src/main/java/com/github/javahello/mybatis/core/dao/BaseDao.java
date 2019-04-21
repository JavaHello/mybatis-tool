package com.github.javahello.mybatis.core.dao;

/**
 * dao 通用方法
 * @author luokai
 * @param <K>
 *     主键
 * @param <M>
 *     实体
 */
public interface BaseDao<K, M> {


    int deleteByPrimaryKey(K id);

    int insert(M record);

    int insertSelective(M record);

    M selectByPrimaryKey(K id);

    int updateByPrimaryKeySelective(M record);

    int updateByPrimaryKey(M record);
}
