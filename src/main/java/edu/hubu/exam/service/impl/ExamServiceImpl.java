package edu.hubu.exam.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.exam.dao.ExamDao;
import edu.hubu.exam.entity.ExamEntity;
import edu.hubu.exam.service.ExamService;


@Service("examService")
public class ExamServiceImpl extends ServiceImpl<ExamDao, ExamEntity> implements ExamService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExamEntity> page = this.page(
                new Query<ExamEntity>().getPage(params),
                new QueryWrapper<ExamEntity>()
        );

        return new PageUtils(page);
    }

}