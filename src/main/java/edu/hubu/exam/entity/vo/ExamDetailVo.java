package edu.hubu.exam.entity.vo;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.exam.entity.ExamCommentEntity;
import edu.hubu.exam.entity.ExamEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ExamDetailVo for
 *
 * @Author yruns
 * @Version 2023/6/21
 */
@Data
public class ExamDetailVo implements Serializable {

    private ExamEntity exam;
    /**
     * 评论
     */
    private PageUtils comments;
}
