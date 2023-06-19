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

import edu.hubu.group.entity.ForumCommentEntity;
import edu.hubu.group.service.ForumCommentService;
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
@RequestMapping("group/forumcomment")
public class ForumCommentController {
    @Autowired
    private ForumCommentService forumCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("group:forumcomment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = forumCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{commentId}")
    // @RequiresPermissions("group:forumcomment:info")
    public R info(@PathVariable("commentId") Long commentId){
		ForumCommentEntity forumComment = forumCommentService.getById(commentId);

        return R.ok().put("forumComment", forumComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("group:forumcomment:save")
    public R save(@RequestBody ForumCommentEntity forumComment){
		forumCommentService.save(forumComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("group:forumcomment:update")
    public R update(@RequestBody ForumCommentEntity forumComment){
		forumCommentService.updateById(forumComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("group:forumcomment:delete")
    public R delete(@RequestBody Long[] commentIds){
		forumCommentService.removeByIds(Arrays.asList(commentIds));

        return R.ok();
    }

}
