package edu.hubu.exam.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * ExamCommentDto for
 *
 * @Author yruns
 * @Version 2023/6/21
 */
@Data
public class ExamCommentDto implements Serializable {

    /**
     * 测评id
     */
    private Long examId;
    /**
     * 评论内容
     */
    private String content;
}
