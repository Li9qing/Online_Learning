package edu.hubu.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@Data
@TableName("submit")
public class SubmitEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long Id;

    /**
     * 测评id
     */
    private Long examId;
    /**
     * 试卷id
     */
    private Long paperId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 问题id
     */
    private Long questionId;
    /**
     * 用户选项
     */
    private String answer;
    /**
     * 提交时间
     */
    private Date submitTime;
    /**
     * 得分
     */
    private Integer score;

}
