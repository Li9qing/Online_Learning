package edu.hubu.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.exception.CustomException;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.exam.dao.PaperDao;
import edu.hubu.exam.entity.PaperEntity;
import edu.hubu.exam.entity.QuestionEntity;
import edu.hubu.exam.entity.QuestionPaperEntity;
import edu.hubu.exam.service.PaperService;
import edu.hubu.exam.service.QuestionPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


@Service("paperService")
public class PaperServiceImpl extends ServiceImpl<PaperDao, PaperEntity> implements PaperService {

    @Autowired
    private QuestionPaperService questionPaperService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Integer type;
        try {
            type = Integer.parseInt((String) params.get("type"));
        } catch (NumberFormatException e) {
            type = null;
        }

        IPage<PaperEntity> page = this.page(
                new Query<PaperEntity>().getPage(params),
                new QueryWrapper<PaperEntity>()
                        .eq(type != null, "type", type)
        );

        return new PageUtils(page);
    }

    @Override
    public void addQuestion(QuestionPaperEntity questionPaper) {
        questionPaperService.save(questionPaper);
    }

    @Override
    public void removeQuestion(QuestionPaperEntity questionPaper) {
        LambdaQueryWrapper<QuestionPaperEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionPaperEntity::getQuestionId, questionPaper.getQuestionId());
        wrapper.eq(QuestionPaperEntity::getPaperId, questionPaper.getPaperId());
        questionPaperService.remove(wrapper);
    }

}