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
@TableName("course_study")
public class CourseStudyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer courseId;
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	private Long lessonId;
	/**
	 * 学习进度
	 */
	private Integer progress;
	/**
	 * 是否完成
	 */
	private Integer finish;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
