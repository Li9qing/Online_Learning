package edu.hubu.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.exam.entity.SubmitEntity;

import java.util.Map;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
public interface SubmitService extends IService<SubmitEntity> {

    PageUtils queryPage(Map<String, Object> params, Long examId, Long userId);

    PageUtils queryPageByQId(Map<String, Object> params, Long qId);

}

