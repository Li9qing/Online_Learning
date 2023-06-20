package edu.hubu.member.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
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
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 重复密码
     */
    @TableField(exist = false)
    private String rePassword;
    /**
     * 封禁状态 1封禁 0正常
     */
    private Integer status;
    /**
     * 是否有教师权限 1有 0无
     */
    private String teacherAccess;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像链接
     */
    private String avatar;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 创建时间
     */
    private Date createTime;

}
