package edu.hubu.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.exam.dao.ExamCommentDao;
import edu.hubu.exam.entity.ExamCommentEntity;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.exam.service.ExamCommentService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ExamCommentServiceImpl for
 *
 * @Author yruns
 * @Version 2023/6/21
 */
@Service("examCommentService")
public class ExamCommentServiceImpl extends ServiceImpl<ExamCommentDao, ExamCommentEntity> implements ExamCommentService {
    @Override
    public PageUtils queryPage(Map<String, Object> params, Long examId) {
        IPage<ExamCommentEntity> page = this.page(
                new Query<ExamCommentEntity>().getPage(params),
                new QueryWrapper<ExamCommentEntity>()
                        .eq(examId != null, "exam_id", examId)
        );

        return new PageUtils(page);
    }
}
