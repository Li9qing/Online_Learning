package edu.hubu.exam.entity;

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
 * @date 2023-06-19 19:59:54
 */
@Data
@TableName("paper")
public class PaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 试卷描述
	 */
	private String description;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 是否删除
	 */
	private Integer isDelete;

}
