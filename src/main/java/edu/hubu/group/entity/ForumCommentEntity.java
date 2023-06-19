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
@TableName("forum_comment")
public class ForumCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long commentId;
	/**
	 * 评论内容
	 */
	private String comment;
	/**
	 * 发布评论的用户id
	 */
	private Long userId;
	/**
	 * 所在论坛
	 */
	private Long forumId;
	/**
	 * 点赞数
	 */
	private Integer likeCount;

}
