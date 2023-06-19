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

import edu.hubu.exam.entity.ScoreEntity;
import edu.hubu.exam.service.ScoreService;
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
@RequestMapping("exam/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("exam:score:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{examId}")
    // @RequiresPermissions("exam:score:info")
    public R info(@PathVariable("examId") Long examId){
		ScoreEntity score = scoreService.getById(examId);

        return R.ok().put("score", score);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("exam:score:save")
    public R save(@RequestBody ScoreEntity score){
		scoreService.save(score);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("exam:score:update")
    public R update(@RequestBody ScoreEntity score){
		scoreService.updateById(score);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("exam:score:delete")
    public R delete(@RequestBody Long[] examIds){
		scoreService.removeByIds(Arrays.asList(examIds));

        return R.ok();
    }

}
