package edu.hubu.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.hubu.course.entity.CourseEntity;
import edu.hubu.course.entity.CourseLessonEntity;
import edu.hubu.course.entity.CourseNoteEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:51:11
 */
@Data
@TableName("course_note")
public class UserNoteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	 public static  UserNoteEntity make(CourseNoteEntity e1, CourseEntity e2, CourseLessonEntity e3){
	 	UserNoteEntity n1 = new UserNoteEntity();
		n1.setCourseId(e1.getCourseId());
		n1.setCoursename(e2.getName());
		n1.setUserId(e1.getUserId());
		n1.setLessonId(e1.getLessonId());
		n1.setChapter(e3.getChapter());
		n1.setContent(e1.getContent());
		n1.setCreateTime(e1.getCreateTime());
		n1.setUpdateTime(e1.getUpdateTime());
		return n1;
	}
	/**
	 * 
	 */
	@TableId
	private Long courseId;
	private String coursename;
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	private Long lessonId;
	private String chapter;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
