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
@TableName("course_teacher")
public class CourseTeacherEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    @TableId
    private Long courseId;
    /**
     * 教师id
     */
    private Long userId;
    /**
     * 教师身份：助教、讲师、教授等
     */
    private Integer duty;
    /**
     * 是否为课程联系人
     */
    private Integer contact;
    /**
     * 创建时间
     */
    private Date createTime;

}
