package edu.hubu.exam.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hubu.exam.entity.QuestionPaperEntity;
import edu.hubu.exam.service.QuestionPaperService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;



/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@RestController
@RequestMapping("exam/questionpaper")
public class QuestionPaperController {
    @Autowired
    private QuestionPaperService questionPaperService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("exam:questionpaper:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionPaperService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{questionId}")
    // @RequiresPermissions("exam:questionpaper:info")
    public R info(@PathVariable("questionId") Long questionId){
		QuestionPaperEntity questionPaper = questionPaperService.getById(questionId);

        return R.ok().put("questionPaper", questionPaper);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("exam:questionpaper:save")
    public R save(@RequestBody QuestionPaperEntity questionPaper){
		questionPaperService.save(questionPaper);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("exam:questionpaper:update")
    public R update(@RequestBody QuestionPaperEntity questionPaper){
		questionPaperService.updateById(questionPaper);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("exam:questionpaper:delete")
    public R delete(@RequestBody Long[] questionIds){
		questionPaperService.removeByIds(Arrays.asList(questionIds));

        return R.ok();
    }

}
