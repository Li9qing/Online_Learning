package edu.hubu.exam.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.exam.entity.PaperEntity;
import edu.hubu.exam.entity.QuestionPaperEntity;
import edu.hubu.exam.service.PaperService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@RestController
@RequestMapping("exam/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = paperService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        PaperEntity paper = paperService.getById(id);

        return R.ok().put("paper", paper);
    }

    /**
     * 创建试卷
     */
    @PostMapping("/save")
    public R save(@RequestBody PaperEntity paper) {
        paper.setCreateTime(new Date());
        paper.setUpdateTime(new Date());
        paper.setIsDelete(0);
        paperService.save(paper);

        return R.ok();
    }

    /**
     * 为试卷添加题目
     */
    @PostMapping("/add_question")
    public R addQuestion(@RequestBody QuestionPaperEntity questionPaper) {

        paperService.addQuestion(questionPaper);
        return R.ok();
    }

    /**
     * 为试卷删除题目
     */
    @PostMapping("/remove_question")
    public R removeQuestion(@RequestBody QuestionPaperEntity questionPaper) {

        paperService.removeQuestion(questionPaper);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody PaperEntity paper) {
        paperService.updateById(paper);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        paperService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
