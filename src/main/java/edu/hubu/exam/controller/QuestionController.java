package edu.hubu.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.common.utils.UserHolder;
import edu.hubu.exam.entity.QuestionEntity;
import edu.hubu.exam.service.QuestionService;
import edu.hubu.member.dto.UserDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@RestController
@RequestMapping("exam/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * 上传题目
     */
    @PostMapping("/save")
    public R save(@RequestBody QuestionEntity question) {

        return questionService.saveQuestion(question);
    }

    /**
     * 列表，根据类型、类别查看，根据题目内容模糊搜索题目。
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = questionService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        QuestionEntity question = questionService.getById(id);

        return R.ok().put("question", question);
    }



    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody QuestionEntity question) {
        questionService.updateById(question);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {

        questionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
