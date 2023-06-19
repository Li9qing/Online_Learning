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
@TableName("course_comment")
public class CourseCommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long courseId;
    /**
     *
     */
    private Long userId;
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
