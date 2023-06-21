package edu.hubu.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.zxing.WriterException;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.exam.entity.dto.ExamSearchDto;
import edu.hubu.exam.entity.ExamEntity;

import java.io.IOException;
import java.util.Map;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
public interface ExamService extends IService<ExamEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ExamEntity release(ExamEntity exam) throws IOException, WriterException;

    R getQRCode(Long id);

    R search(Map<String, Object> params, ExamSearchDto examSearchDto);

    R detail(Map<String, Object> params, Long examId);
}

