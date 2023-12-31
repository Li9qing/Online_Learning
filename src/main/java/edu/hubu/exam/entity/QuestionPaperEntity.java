package edu.hubu.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@Data
@TableName("question_paper")
public class QuestionPaperEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 题目id
     */
    private Long questionId;
    /**
     * 分数
     */
    private Integer questionScore;
    /**
     * 试卷id
     */
    private Long paperId;

}
