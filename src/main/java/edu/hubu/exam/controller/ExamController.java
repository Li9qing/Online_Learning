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

import edu.hubu.exam.entity.ExamEntity;
import edu.hubu.exam.service.ExamService;
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
@RequestMapping("exam/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("exam:exam:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = examService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("exam:exam:info")
    public R info(@PathVariable("id") Long id){
		ExamEntity exam = examService.getById(id);

        return R.ok().put("exam", exam);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("exam:exam:save")
    public R save(@RequestBody ExamEntity exam){
		examService.save(exam);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("exam:exam:update")
    public R update(@RequestBody ExamEntity exam){
		examService.updateById(exam);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("exam:exam:delete")
    public R delete(@RequestBody Long[] ids){
		examService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
