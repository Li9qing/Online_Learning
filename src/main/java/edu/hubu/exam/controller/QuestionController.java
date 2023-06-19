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

import edu.hubu.exam.entity.QuestionEntity;
import edu.hubu.exam.service.QuestionService;
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
@RequestMapping("exam/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("exam:question:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("exam:question:info")
    public R info(@PathVariable("id") Long id){
		QuestionEntity question = questionService.getById(id);

        return R.ok().put("question", question);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("exam:question:save")
    public R save(@RequestBody QuestionEntity question){
		questionService.save(question);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("exam:question:update")
    public R update(@RequestBody QuestionEntity question){
		questionService.updateById(question);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("exam:question:delete")
    public R delete(@RequestBody Long[] ids){
		questionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
