package edu.hubu.course.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:51:11
 */
@Data
@TableName("course_lesson")
public class CourseLessonEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 课时编号
     */
    @TableId
    private Long id;
    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 创建用户
     */
    private Long createUser;
    /**
     * title
     */
    private String title;
    /**
     * 课时章节
     */
    private String chapter;
    /**
     * 课程内容
     */
    private String content;
    /**
     * 课程类型
     */
    private Integer type;
    /**
     * 发布状态
     */
    private Integer visable;
    /**
     * 课时资源
     */
    private Integer resourceId;
    /**
     * 课时展示时间
     */
    private Date displayTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
