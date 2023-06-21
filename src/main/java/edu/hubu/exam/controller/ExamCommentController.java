package edu.hubu.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.R;
import edu.hubu.common.utils.UserHolder;
import edu.hubu.exam.entity.ExamCommentEntity;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.exam.entity.dto.ExamCommentDto;
import edu.hubu.exam.service.ExamCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * ExamCommentController for
 *
 * @Author yruns
 * @Version 2023/6/21
 */
@RestController
@RequestMapping("exam/comment")
public class ExamCommentController {

    @Autowired
    private ExamCommentService examCommentService;

    /**
     * 获取测评下的所有评论（分页）
     */
    @GetMapping("/list/{id}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("id") Long examId) {
        PageUtils page = examCommentService.queryPage(params, examId);

        return R.ok().put("page", page);
    }

    /**
     * 评测后用户进行评价
     */
    @PostMapping("/remark")
    public R comment(@RequestBody ExamCommentDto examCommentDto) {
        ExamCommentEntity examCommentEntity = new ExamCommentEntity();
        examCommentEntity.setUserId(UserHolder.getUser().getId());
        examCommentEntity.setCreateTime(new Date());
        examCommentEntity.setExamId(examCommentDto.getExamId());
        examCommentEntity.setContent(examCommentDto.getContent());
        examCommentService.save(examCommentEntity);

        return R.ok();
    }
}
