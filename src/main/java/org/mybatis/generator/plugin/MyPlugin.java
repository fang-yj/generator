package org.mybatis.generator.plugin;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fyj
 * @date 2020/7/19.
 */
public class MyPlugin extends PluginAdapter {
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	/**
	 * 实体类字段：
	 * <p>追加日期格式化注解</p>
	 * <p>tinyint数据（Byte）转换成（Integer）类型</p>
	 * @param field
	 * @param topLevelClass
	 * @param introspectedColumn
	 * @param introspectedTable
	 * @param modelClassType
	 * @return
	 */
	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		if ("TIMESTAMP".equals(introspectedColumn.getJdbcTypeName())) {
			field.addAnnotation(Annotation.DateTimeFormat.getAnnotation() + "(pattern = \"yyyy-MM-dd HH:mm:ss\")");
			topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.DateTimeFormat.getClazz()));
		}
		String a = field.getType().getShortName();
		if (StringUtils.equals("Byte", a)) {
			field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
		}

		return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
	}

	/**
	 * 类注释生成
	 * @param topLevelClass
	 * @param introspectedTable
	 * @return
	 */
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		classAnnotation(topLevelClass, introspectedTable);
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	/**
	 * 接口注释生成
	 * @param interfaze
	 * @param topLevelClass
	 * @param introspectedTable
	 * @return
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		interfazeAnnotation(interfaze, introspectedTable);
		Set<FullyQualifiedJavaType> set = new HashSet<FullyQualifiedJavaType>();
		set.add(new FullyQualifiedJavaType(Annotation.Mapper.getClazz()));
		interfaze.addImportedTypes(set);
		interfaze.addAnnotation(Annotation.Mapper.getAnnotation());
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	/**
	 * 类注释生成
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	private static void classAnnotation(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * " + topLevelClass.getType().getShortName());
		topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
		topLevelClass.addJavaDocLine(" * @author " + System.getProperty("user.name"));
		topLevelClass.addJavaDocLine(" * @created Create Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		topLevelClass.addJavaDocLine(" */");
	}

	/**
	 * 接口注释生成
	 * @param interfaze
	 * @param introspectedTable
	 */
	private static void interfazeAnnotation(Interface interfaze, IntrospectedTable introspectedTable) {
		interfaze.addJavaDocLine("/**");
		interfaze.addJavaDocLine(" * " + interfaze.getType().getShortName());
		interfaze.addJavaDocLine(" * " + introspectedTable.getRemarks());
		interfaze.addJavaDocLine(" * @author " + System.getProperty("user.name"));
		interfaze.addJavaDocLine(" * @created Create Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		interfaze.addJavaDocLine(" */");
	}
}
