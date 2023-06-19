package edu.hubu.course.entity;

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
 * @date 2023-06-19 20:51:11
 */
@Data
@TableName("course_resource")
public class CourseResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 文件名
	 */
	private String filename;
	/**
	 * 资源类型
	 */
	private Integer type;
	/**
	 * 资源状态
	 */
	private Integer mode;
	/**
	 * 发布状态
	 */
	private Integer visable;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
