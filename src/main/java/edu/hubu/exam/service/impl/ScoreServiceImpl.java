package edu.hubu.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.R;
import edu.hubu.exam.dao.ScoreDao;
import edu.hubu.exam.dto.ManualReviewDto;
import edu.hubu.exam.entity.QuestionEntity;
import edu.hubu.exam.entity.QuestionPaperEntity;
import edu.hubu.exam.entity.ScoreEntity;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.exam.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service("scoreService")
public class ScoreServiceImpl extends ServiceImpl<ScoreDao, ScoreEntity> implements ScoreService {

    @Autowired
    private SubmitService submitService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionPaperService questionPaperService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ScoreEntity> page = this.page(
                new Query<ScoreEntity>().getPage(params),
                new QueryWrapper<ScoreEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void autoReview(Long examId) {
        // 根据examId找到paper_id
        Long paperId = examService.getById(examId).getPaperId();

        // 根据paperId找到本次测评的所有题目及分数
        HashMap<Long, Integer> scoreMap = new HashMap<>();
        List<QuestionPaperEntity> questionPaperList = questionPaperService.query()
                .eq("paper_id", paperId).list();
        questionPaperList.forEach(qp -> {
            scoreMap.put(qp.getQuestionId(), qp.getQuestionScore());
        });

        // 根据examId找到所有的question_id
        List<SubmitEntity> items = submitService.query().eq("exam_id", examId).list();

        // 将items中所有question_id收集为一个set
        HashSet<Long> questionSet = new HashSet<>();
        items.forEach(item -> {
            questionSet.add(item.getQuestionId());
        });
        // 查询到所有的question答案
        HashMap<Long, String> answerMap = new HashMap<>();
        List<QuestionEntity> questionEntities = questionService.getBaseMapper().selectBatchIds(questionSet);
        questionEntities.forEach(q -> {
            if (q.getType() == 1) {
                // 只改客观题
                answerMap.put(q.getId(), q.getAnswer());
            }
        });

        // 为submit打分
        items.forEach(item -> {
            Long qId = item.getQuestionId();
            if (answerMap.containsKey(qId)) {
                // 只改客观题
                if (answerMap.get(qId).equals(item.getAnswer())) {
                    // 给该题的满分
                    item.setScore(scoreMap.get(qId));
                } else {
                    item.setScore(0);
                }
            }
        });


        // 将items写回submit表
        submitService.updateBatchById(items);
    }

    @Override
    public R manualReview(ManualReviewDto manualReviewDto) {
        try {
            SubmitEntity submit = submitService.query()
                    .eq("exam_id", manualReviewDto.getExamId())
                    .eq("question_id", manualReviewDto.getQuestionId())
                    .eq("user_id", manualReviewDto.getUserId())
                    .one();
            submit.setScore(manualReviewDto.getScore());
            submit.setComment(manualReviewDto.getComment());

            submitService.updateById(submit);
            return R.ok();
        } catch (Exception e) {
            return R.error("评分出错");
        }

    }

    @Override
    public R submitExam(Long examId, Long userId) {
        List<SubmitEntity> submits = submitService.query()
                .eq("exam_id", examId)
                .eq("user_id", userId)
                .list();

        ScoreEntity scoreEntity = new ScoreEntity();
        int score = 0;
        for (SubmitEntity submit : submits) {
            score += submit.getScore() != null ? submit.getScore() : 0;
        }
        scoreEntity.setExamId(examId);
        scoreEntity.setUserId(userId);
        scoreEntity.setScore(score);
        // todo 从redis中获取开始时间和结束时间
        scoreEntity.setStartTime(new Date());
        scoreEntity.setFinishTime(new Date());

        // 将该记录写入到数据库
        this.save(scoreEntity);
        return R.ok();
    }

    @Override
    public PageUtils listPassedPage(Map<String, Object> params, Long examId) {
        Integer passScore = examService.getById(examId).getPassScore();
        IPage<ScoreEntity> page = this.page(
                new Query<ScoreEntity>().getPage(params),
                new QueryWrapper<ScoreEntity>()
                        .eq("exam_id", examId)
                        .ge("score", passScore)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listFailedPage(Map<String, Object> params, Long examId) {
        Integer passScore = examService.getById(examId).getPassScore();
        IPage<ScoreEntity> page = this.page(
                new Query<ScoreEntity>().getPage(params),
                new QueryWrapper<ScoreEntity>()
                        .eq("exam_id", examId)
                        .lt("score", passScore)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listPage(Map<String, Object> params, Long examId) {
        IPage<ScoreEntity> page = this.page(
                new Query<ScoreEntity>().getPage(params),
                new QueryWrapper<ScoreEntity>()
                        .eq("exam_id", examId)
        );

        return new PageUtils(page);
    }

}