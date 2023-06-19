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
@TableName("group_plan")
public class GroupPlanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long planId;
	/**
	 * 
	 */
	private Long groupId;
	/**
	 * 学习计划
	 */
	private String content;
	/**
	 * 
	 */
	private Date startTime;
	/**
	 * 
	 */
	private Date endTime;

}
