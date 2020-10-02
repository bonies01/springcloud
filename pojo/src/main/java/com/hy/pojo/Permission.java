package com.hy.pojo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 权限
 * 
 * @author Administrator
 *
 */
@Data
public class Permission implements Serializable {

	private static final long serialVersionUID = -2776239773078200867L;

	private Long id;

	@ApiModelProperty("权限名称")
	private String name;

	@ApiModelProperty("权限字符串")
	private String code;

	@ApiModelProperty("资源类型")
	private Integer type;

	@ApiModelProperty("URL")
	private String url;

	@ApiModelProperty("排序")
	private Integer sort;

	@ApiModelProperty("父菜单ID")
	private Long pid;

}
