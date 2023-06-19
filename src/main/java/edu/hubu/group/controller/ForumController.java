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

import edu.hubu.group.entity.ForumEntity;
import edu.hubu.group.service.ForumService;
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
@RequestMapping("group/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("group:forum:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = forumService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{forumId}")
    // @RequiresPermissions("group:forum:info")
    public R info(@PathVariable("forumId") Long forumId){
		ForumEntity forum = forumService.getById(forumId);

        return R.ok().put("forum", forum);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("group:forum:save")
    public R save(@RequestBody ForumEntity forum){
		forumService.save(forum);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("group:forum:update")
    public R update(@RequestBody ForumEntity forum){
		forumService.updateById(forum);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("group:forum:delete")
    public R delete(@RequestBody Long[] forumIds){
		forumService.removeByIds(Arrays.asList(forumIds));

        return R.ok();
    }

}
