package edu.hubu.message.entity;

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
 * @date 2023-06-19 20:55:45
 */
@Data
@TableName("message_attr")
public class MessageAttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long messageId;
	/**
	 * 发送方
	 */
	private Long from;
	/**
	 * 接收方
	 */
	private Long to;
	/**
	 * 是否已读
	 */
	private Integer isRead;

}
