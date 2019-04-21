# 是什么

mybatis 插件，使生成的 mapper 接口， 和实体类，继承通用接口，方便开发。

# 使用

将此项目导入到自己的项目中,在自己的 `generator.xml` 文件添加

```xml
        <plugin type="com.github.javahello.mybatis.plugins.BaseDaoPlugin" >
        </plugin>
```
