package edu.hubu.exam.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * ManualReviewDto for
 *
 * @Author yruns
 * @Version 2023/6/21
 */
@Data
public class ManualReviewDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 评测id
     */
    private Long examId;
    /**
     * 题目id
     */
    private Long questionId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 得分
     */
    private Integer score;
    /**
     * 评语
     */
    private String comment;
}
