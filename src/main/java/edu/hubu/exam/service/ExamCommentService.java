package edu.hubu.exam.service;


import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.exam.entity.ExamCommentEntity;
import edu.hubu.exam.entity.ExamEntity;

import java.util.List;
import java.util.Map;

public interface ExamCommentService extends IService<ExamCommentEntity> {
    PageUtils queryPage(Map<String, Object> params, Long examId);

}
