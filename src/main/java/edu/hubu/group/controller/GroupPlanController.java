package edu.hubu.group.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.group.entity.GroupPlanEntity;
import edu.hubu.group.service.GroupPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
@RestController
@RequestMapping("group/plan")
public class GroupPlanController {
    @Autowired
    private GroupPlanService groupPlanService;

    /**
     * 添加学习计划
     */
    @PostMapping("/save")
    public R save(@RequestBody GroupPlanEntity groupPlan) {
        groupPlanService.save(groupPlan);

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = groupPlanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{planId}")
    public R info(@PathVariable("planId") Long planId) {
        GroupPlanEntity groupPlan = groupPlanService.getById(planId);

        return R.ok().put("groupPlan", groupPlan);
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GroupPlanEntity groupPlan) {
        groupPlanService.updateById(groupPlan);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] planIds) {
        groupPlanService.removeByIds(Arrays.asList(planIds));

        return R.ok();
    }

}
