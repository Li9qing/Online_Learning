package edu.hubu.exam.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.exam.entity.ScoreEntity;
import edu.hubu.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@RestController
@RequestMapping("exam/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;



    /**
     * 信息
     */
    @RequestMapping("/info/{examId}")
    public R info(@PathVariable("examId") Long examId) {
        ScoreEntity score = scoreService.getById(examId);

        return R.ok().put("score", score);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ScoreEntity score) {
        scoreService.save(score);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ScoreEntity score) {
        scoreService.updateById(score);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] examIds) {
        scoreService.removeByIds(Arrays.asList(examIds));

        return R.ok();
    }

}
