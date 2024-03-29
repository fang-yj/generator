package org.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.List;

/**
 * @author fyj
 * @create 2019-11-20 17:12
 */
public class CustomeCommentGenerator extends DefaultCommentGenerator {

	/**
	 * *mapper.class 生成方法注释
	 * @param method
	 * @param introspectedTable
	 */
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		method.addJavaDocLine("/**");
		String method_name = method.getName();

		if ("selectByExample".equals(method_name)) {
			method.addJavaDocLine(" * 根据条件查询列表");
		} else if (method_name.startsWith("updateByExample")) {
			method.addJavaDocLine(" * 选择性更新数据库记录");
		} else if ("updateByPrimaryKeySelective".equals(method_name)) {
			method.addJavaDocLine(" * 根据主键来更新部分数据库记录");
		} else if ("deleteByPrimaryKey".equals(method_name)) {
			method.addJavaDocLine(" * 根据主键删除数据库的记录");
		} else if ("insert".equals(method_name)) {
			method.addJavaDocLine(" * 插入数据库记录");
		} else if ("selectByPrimaryKey".equals(method_name)) {
			method.addJavaDocLine(" * 根据主键获取一条数据库记录");
		} else if ("updateByPrimaryKey".equals(method_name)) {
			method.addJavaDocLine(" * 根据主键来更新数据库记录");
		} else if ("selectAll".equals(method_name)) {
			method.addJavaDocLine(" * 获取全部数据库记录");
		} else if ("countByExample".equals(method_name)) {
			method.addJavaDocLine(" * 根据条件计数");
		} else if ("insertSelective".equals(method_name)) {
			method.addJavaDocLine(" * 插入数据库记录");
		}
		method.addJavaDocLine(" *");
		List<Parameter> parameterList = method.getParameters();
		String paramterName;
		for (Parameter parameter : parameterList) {
			paramterName = parameter.getName();
			method.addJavaDocLine(" * @param " + paramterName);
			method.addJavaDocLine(" * @return");
		}
		method.addJavaDocLine(" */");
	}

	/**
	 * 添加字段注释
	 * @param field
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		StringBuffer sb = new StringBuffer();
		field.addJavaDocLine("/**");
		if (introspectedColumn.getRemarks() != null)
			field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
		sb.append(" * 表 : ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		field.addJavaDocLine(sb.toString());
		field.addJavaDocLine(" * 对应字段 : " + introspectedColumn.getActualColumnName());
		// addJavadocTag(field, false);
		field.addJavaDocLine(" */");
	}

	/**
	 * get方法注释
	 * @param method
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		StringBuffer sb = new StringBuffer();
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * get method ");
		method.addJavaDocLine(" *");
		sb = new StringBuffer();
		sb.append(" * @return ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		sb.append('.');
		sb.append(introspectedColumn.getActualColumnName());
		sb.append("：");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());
		// addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	/**
	 * set方法注释
	 * @param method
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * set method ");
		method.addJavaDocLine(" *");
		Parameter parm = method.getParameters().get(0);
		method.addJavaDocLine(" * @param " + parm.getName() + "  " + introspectedColumn.getRemarks());
		// addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	/**
	 * *mapper.xml 注释
	 * @param xmlElement
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
		// xmlElement.addElement(new TextElement("<!--")); //$NON-NLS-1$
		//
		// StringBuilder sb = new StringBuilder();
		// sb.append("  " + MergeConstants.NEW_ELEMENT_TAG);
		// xmlElement.addElement(new TextElement(sb.toString()));
		// String s = getDateString();
		// if (s != null) {
		// 	sb.setLength(0);
		// 	sb.append("  This element is automatically generated by MyBatis Generator,Do not modify ! Generated on "); //$NON-NLS-1$
		// 	sb.append(s);
		// 	sb.append('.');
		// 	xmlElement.addElement(new TextElement(sb.toString()));
		// }
		//
		// xmlElement.addElement(new TextElement("-->")); //$NON-NLS-1$
	}
}
