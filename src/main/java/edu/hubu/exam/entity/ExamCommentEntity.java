package edu.hubu.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ExamCommentEntity for
 *
 * @Author yruns
 * @Version 2023/6/21
 */
@Data
@TableName("exam_comment")
public class ExamCommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId
    private Long Id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 发布评论的用户id
     */
    private Long userId;
    /**
     * 所属评测
     */
    private Long examId;
    /**
     * 点赞数
     */
    private Date createTime;

}
