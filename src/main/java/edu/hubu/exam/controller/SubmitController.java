package edu.hubu.exam.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.exam.service.SubmitService;
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
@RequestMapping("exam/submit")
public class SubmitController {
    @Autowired
    private SubmitService submitService;

    /**
     * 根据用户id，评测id查看提交的答案
     */
    @GetMapping("/list/{examId}/{userId}")
    public R listByExamAndUser(@RequestParam Map<String, Object> params, @PathVariable Long examId, @PathVariable Long userId) {
        if (examId == null || userId == null) {
            return R.error("测评id或用户id不能为空");
        }
        PageUtils page = submitService.queryPage(params, examId, userId);

        return R.ok().put("page", page);
    }

    /**
     * 根据题目id查看提交的答案
     */
    @GetMapping("/list/q/{q_id}")
    public R listByQId(@RequestParam Map<String, Object> params, @PathVariable("q_id") Long qId) {
        if (qId == null) {
            return R.error("测评id或用户id不能为空");
        }
        PageUtils page = submitService.queryPageByQId(params, qId);

        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{examId}")
    public R info(@PathVariable("examId") Long examId) {
        SubmitEntity submit = submitService.getById(examId);

        return R.ok().put("submit", submit);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SubmitEntity submit) {
        submitService.save(submit);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SubmitEntity submit) {
        submitService.updateById(submit);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] examIds) {
        submitService.removeByIds(Arrays.asList(examIds));

        return R.ok();
    }

}
