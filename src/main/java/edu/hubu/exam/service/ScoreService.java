package edu.hubu.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.exam.dto.ManualReview;
import edu.hubu.exam.entity.ScoreEntity;

import java.util.Map;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
public interface ScoreService extends IService<ScoreEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void autoReview(Long examId);

    R manualReview(ManualReview manualReview);
}

