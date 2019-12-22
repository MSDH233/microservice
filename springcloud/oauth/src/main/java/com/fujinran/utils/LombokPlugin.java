package com.fujinran.utils;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */

/**
 * 如果需要使用则需要在maven的plugin里指定
 */
public class LombokPlugin extends PluginAdapter{

    public LombokPlugin() {
    }
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addAnnotation("@Data");
        //topLevelClass.addImportedType("lombok.Getter");
        //topLevelClass.addImportedType("lombok.Setter");
        //topLevelClass.addImportedType("lombok.ToString");
        //topLevelClass.addAnnotation("@Getter");
        //topLevelClass.addAnnotation("@Setter");
        //topLevelClass.addAnnotation("@ToString");
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* Created by Mybatis Generator " + this.date2Str(new Date()));
        topLevelClass.addJavaDocLine("*/");
        return true;
    }

    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* Created by Mybatis Generator " + this.date2Str(new Date()));
        interfaze.addJavaDocLine("*/");
        return true;
    }

    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    private String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
}