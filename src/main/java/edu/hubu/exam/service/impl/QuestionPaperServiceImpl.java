package edu.hubu.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.exam.dao.QuestionPaperDao;
import edu.hubu.exam.entity.QuestionPaperEntity;
import edu.hubu.exam.service.QuestionPaperService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("questionPaperService")
public class QuestionPaperServiceImpl extends ServiceImpl<QuestionPaperDao, QuestionPaperEntity> implements QuestionPaperService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QuestionPaperEntity> page = this.page(
                new Query<QuestionPaperEntity>().getPage(params),
                new QueryWrapper<QuestionPaperEntity>()
        );

        return new PageUtils(page);
    }

}