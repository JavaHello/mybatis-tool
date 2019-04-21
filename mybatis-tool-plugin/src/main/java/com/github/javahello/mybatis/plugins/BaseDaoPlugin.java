package com.github.javahello.mybatis.plugins;

import com.github.javahello.mybatis.core.dao.BaseDao;
import com.github.javahello.mybatis.core.model.BaseModel;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

public class BaseDaoPlugin extends PluginAdapter {

    String baseDao;

    String baseModel;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.baseDao = properties.getProperty("baseDao");
        this.baseModel = properties.getProperty("baseModel");
        if (baseDao == null) {
            baseDao = BaseDao.class.getCanonicalName();
        }
        if (baseModel == null) {
            baseModel = BaseModel.class.getCanonicalName();
        }
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType(new FullyQualifiedJavaType(baseModel));
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType(baseModel));
        return true;
    }


    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        interfaze.addImportedType(new FullyQualifiedJavaType(baseDao));
        String pkType = null;
        List<Method> methods = interfaze.getMethods();
        for (Method method : methods) {
            if(method.getName().endsWith("selectByPrimaryKey")) {
                pkType = method.getParameters().get(0).getType().getFullyQualifiedName();
                break;
            }
        }
        String baseDaoS = baseDao + "<" + pkType + ","
                + introspectedTable.getBaseRecordType() + ">";
        interfaze.addSuperInterface(new FullyQualifiedJavaType(baseDaoS));

        return true;
    }

}
