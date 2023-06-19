package edu.hubu.exam.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.exam.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("exam/submit")
public class SubmitController {
    @Autowired
    private SubmitService submitService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("exam:submit:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = submitService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{examId}")
    // @RequiresPermissions("exam:submit:info")
    public R info(@PathVariable("examId") Long examId){
		SubmitEntity submit = submitService.getById(examId);

        return R.ok().put("submit", submit);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("exam:submit:save")
    public R save(@RequestBody SubmitEntity submit){
		submitService.save(submit);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("exam:submit:update")
    public R update(@RequestBody SubmitEntity submit){
		submitService.updateById(submit);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("exam:submit:delete")
    public R delete(@RequestBody Long[] examIds){
		submitService.removeByIds(Arrays.asList(examIds));

        return R.ok();
    }

}
