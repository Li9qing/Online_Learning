package edu.hubu.group.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.group.entity.ForumEntity;
import edu.hubu.group.service.ForumService;
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
@RequestMapping("group/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;

    /**
     * 发表话题
     */
    @RequestMapping("/save")
    public R save(@RequestBody ForumEntity forum) {
        forumService.save(forum);

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = forumService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{forumId}")
    public R info(@PathVariable("forumId") Long forumId) {
        ForumEntity forum = forumService.getById(forumId);

        return R.ok().put("forum", forum);
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ForumEntity forum) {
        forumService.updateById(forum);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] forumIds) {
        forumService.removeByIds(Arrays.asList(forumIds));

        return R.ok();
    }

}
