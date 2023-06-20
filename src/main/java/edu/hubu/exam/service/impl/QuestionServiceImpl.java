package edu.hubu.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.exception.CustomException;
import edu.hubu.common.utils.*;
import edu.hubu.exam.dao.QuestionDao;
import edu.hubu.exam.entity.QuestionEntity;
import edu.hubu.exam.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("questionService")
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, QuestionEntity> implements QuestionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // 解析分页参数
        PageParams pageParams = PageParams.parsePageParams(params);
        int pageNum = pageParams.getPageNum();
        int pageSize = pageParams.getPageSize();

        Integer type;
        try {
            type = Integer.parseInt((String) params.get("type"));
        } catch (NumberFormatException e) {
            type = null;
        }
        String key = (String) params.get("key");

        // 构造条件表达式
        LambdaQueryWrapper<QuestionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(type != null, QuestionEntity::getType, type);
        wrapper.like(key != null, QuestionEntity::getContent, key);

        // 分页查询
        Page<QuestionEntity> page = baseMapper
                .selectPage(new Page<>(pageNum, pageSize), wrapper);

        return new PageUtils(page);
    }

    @Override
    public R saveQuestion(QuestionEntity question) {
        question.setCreateTime(new Date());
        question.setUpdateTime(new Date());
        question.setIsDelete(0);
        boolean save = this.save(question);
        if (!save) {
            return R.error("题目上传失败");
        }

        return R.ok();
    }

}