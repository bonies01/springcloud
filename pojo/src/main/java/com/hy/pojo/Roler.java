package com.hy.pojo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色
 * @author Administrator
 *
 */
@Data
public class Roler implements Serializable {

	private static final long serialVersionUID = 8800738238234753937L;
	
	@ApiModelProperty("角色编码")
    private String roleCode;

	@ApiModelProperty("角色名称")
    private String roleName;
	
	@ApiModelProperty("权限集合")
	private List<Permission> permList;
}
