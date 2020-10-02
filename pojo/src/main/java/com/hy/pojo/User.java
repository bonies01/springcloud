package com.hy.pojo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User {
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("用户编号")
	private String usercode;
	
	@ApiModelProperty("用户名")
	private String  username;
	
	@ApiModelProperty("密码")
	private String password;
	
	@ApiModelProperty("角色")
	private List<Roler> rolerList;
	
	@ApiModelProperty("盐") 
	private String salt;
	
	
}
