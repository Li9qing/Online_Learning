package edu.hubu.group.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.group.entity.ForumCommentEntity;
import edu.hubu.group.service.ForumCommentService;
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
@RequestMapping("group/forumcomment")
public class ForumCommentController {
    @Autowired
    private ForumCommentService forumCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = forumCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{commentId}")
    public R info(@PathVariable("commentId") Long commentId) {
        ForumCommentEntity forumComment = forumCommentService.getById(commentId);

        return R.ok().put("forumComment", forumComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ForumCommentEntity forumComment) {
        forumCommentService.save(forumComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ForumCommentEntity forumComment) {
        forumCommentService.updateById(forumComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] commentIds) {
        forumCommentService.removeByIds(Arrays.asList(commentIds));

        return R.ok();
    }

}
