package edu.hubu.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * 
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
//	@ApiModelProperty(value = "管理员id")
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;
	/**
	 * 封禁状态 1封禁 0正常
	 */
	@ApiModelProperty(value = "是否删除")
//	@TableLogic(value = "0",delval = "1")
	private Integer status;
	/**
	 * 是否有教师权限 1有 0无
	 */
	@ApiModelProperty(value = "角色，student和teacher两种")
	private String teacherAccess;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 头像链接
	 */
	@ApiModelProperty(value = "用户头像地址")
	private String avatar;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

}
