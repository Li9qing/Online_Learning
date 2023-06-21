package edu.hubu.exam.controller;

import com.google.zxing.WriterException;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.exam.entity.ExamEntity;
import edu.hubu.exam.service.ExamService;
import edu.hubu.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@RestController
@RequestMapping("exam/test")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ScoreService scoreService;

    /**
     * 查看用户的测评开始时间，测评完成时间以及用户对测评的评价
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = scoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 发布测评
     */
    @PostMapping("/release")
    public R release(@RequestBody ExamEntity e) {

        ExamEntity exam = null;
        try {
            exam = examService.release(e);
        } catch (IOException | WriterException ex) {
            throw new RuntimeException(ex);
        }

        return R.ok().put("exam", exam);
    }

    /**
     * 获取测评二维码
     */
    @GetMapping("/qr/{id}")
    public R getQRCode(@PathVariable Long id) {

        return examService.getQRCode(id);
    }

    /**
     * 创建测评
     */
    @PostMapping("/save")
    public R save(@RequestBody ExamEntity exam) {
        exam.setIsDelete(0);
        exam.setCreateTime(new Date());
        exam.setUpdateTime(new Date());
        examService.save(exam);

        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ExamEntity exam) {
        examService.updateById(exam);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        examService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
