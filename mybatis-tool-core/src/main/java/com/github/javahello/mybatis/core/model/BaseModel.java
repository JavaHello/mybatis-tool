package com.github.javahello.mybatis.core.model;

import java.util.Date;

/**
 *
 * 基本表结构必须有的字段
 * @author luokai
 */
public interface BaseModel {


    Date getCreateDatetime();

    void setCreateDatetime(Date datetime);

    Date getLastUpdateDatetime();

    void setLastUpdateDatetime(Date datetime);
}
