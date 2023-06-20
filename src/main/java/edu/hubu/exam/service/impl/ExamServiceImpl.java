package edu.hubu.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.exception.CustomException;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.RedisKeys;
import edu.hubu.exam.dao.ExamDao;
import edu.hubu.exam.entity.ExamEntity;
import edu.hubu.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("examService")
public class ExamServiceImpl extends ServiceImpl<ExamDao, ExamEntity> implements ExamService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExamEntity> page = this.page(
                new Query<ExamEntity>().getPage(params),
                new QueryWrapper<ExamEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ExamEntity release(ExamEntity e) {
        ExamEntity exam = this.getById(e.getId());
        if (exam.getShowStatus() == 1) {
            CustomException.cast("该测评已发布，不能重复发布");
        }
        // 设置发布状态
        exam.setShowStatus(1);
        // 设置测评过期时间
        exam.setExpireAt(e.getExpireAt());
        this.updateById(exam);

        // 生成二维码


        // 将二维码写入redis
        redisTemplate.opsForSet().add(RedisKeys.EXAM_QRCODE + e.getId().toString(), null);

        return exam;
    }


}