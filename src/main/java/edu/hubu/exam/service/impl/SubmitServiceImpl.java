package edu.hubu.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.R;
import edu.hubu.exam.dao.SubmitDao;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.exam.service.SubmitService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("submitService")
public class SubmitServiceImpl extends ServiceImpl<SubmitDao, SubmitEntity> implements SubmitService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long examId, Long userId) {

        IPage<SubmitEntity> page = this.page(
                new Query<SubmitEntity>().getPage(params),
                new QueryWrapper<SubmitEntity>()
                        .eq("exam_id", examId)
                        .eq("user_id", userId)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByQId(Map<String, Object> params, Long qId) {
        IPage<SubmitEntity> page = this.page(
                new Query<SubmitEntity>().getPage(params),
                new QueryWrapper<SubmitEntity>()
                        .eq("question_id", qId)
        );

        return new PageUtils(page);
    }


}