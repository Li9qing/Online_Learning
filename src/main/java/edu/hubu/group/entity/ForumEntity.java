package edu.hubu.group.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
@Data
@TableName("forum")
public class ForumEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 论坛id
	 */
	@TableId
	private Long forumId;
	/**
	 * 论坛标题
	 */
	private String title;
	/**
	 * 论坛内容
	 */
	private String description;
	/**
	 * 点赞数
	 */
	private Integer likeCount;
	/**
	 * 创建用户id
	 */
	private Long createUserId;

}
