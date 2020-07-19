package org.mybatis.generator.plugin;


/**
 * Created by fyj on 2020/7/18.
 */
public enum Annotation {
	// DATA("@Data", "lombok.Data"),
	Mapper("@Mapper", "org.apache.ibatis.annotations.Mapper"), // Param("@Param", "org.apache.ibatis.annotations.Param"),
	// ApiModel("@ApiModel", "io.swagger.annotations.ApiModel"),
	// ApiModelProperty("@ApiModelProperty", "io.swagger.annotations.ApiModelProperty"),
	// JsonFormat("@JsonFormat", "com.fasterxml.jackson.annotation.JsonFormat"),
	DateTimeFormat("@DateTimeFormat", "org.springframework.format.annotation.DateTimeFormat");

	private String annotation;

	private String clazz;

	Annotation(String annotation, String clazz) {
		this.annotation = annotation;
		this.clazz = clazz;
	}

	public String getAnnotation() {
		return annotation;
	}

	public String getClazz() {
		return clazz;
	}
}
