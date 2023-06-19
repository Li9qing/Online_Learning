package edu.hubu.group.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hubu.group.entity.GroupPlanEntity;
import edu.hubu.group.service.GroupPlanService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;



/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
@RestController
@RequestMapping("group/groupplan")
public class GroupPlanController {
    @Autowired
    private GroupPlanService groupPlanService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("group:groupplan:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = groupPlanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{planId}")
    // @RequiresPermissions("group:groupplan:info")
    public R info(@PathVariable("planId") Long planId){
		GroupPlanEntity groupPlan = groupPlanService.getById(planId);

        return R.ok().put("groupPlan", groupPlan);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("group:groupplan:save")
    public R save(@RequestBody GroupPlanEntity groupPlan){
		groupPlanService.save(groupPlan);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("group:groupplan:update")
    public R update(@RequestBody GroupPlanEntity groupPlan){
		groupPlanService.updateById(groupPlan);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("group:groupplan:delete")
    public R delete(@RequestBody Long[] planIds){
		groupPlanService.removeByIds(Arrays.asList(planIds));

        return R.ok();
    }

}
