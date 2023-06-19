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
@TableName("course")
public class CourseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程简介
     */
    private String summary;
    /**
     * 课程评分
     */
    private Double score;
    /**
     * 发布状态
     */
    private Integer visable;
    /**
     *
     */
    private Date startTime;
    /**
     *
     */
    private Date endTime;
    /**
     *
     */
    private Date finishTime;
    /**
     *
     */
    private Date createTime;
    /**
     * 创建人id
     */
    private Long createUser;
    /**
     * 更新时间
     */
    private Date updateTime;

}
