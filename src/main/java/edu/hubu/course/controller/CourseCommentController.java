package edu.hubu.course.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hubu.course.entity.CourseCommentEntity;
import edu.hubu.course.service.CourseCommentService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;



/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:51:11
 */
@RestController
@RequestMapping("course/coursecomment")
public class CourseCommentController {
    @Autowired
    private CourseCommentService courseCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("course:coursecomment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    // @RequiresPermissions("course:coursecomment:info")
    public R info(@PathVariable("courseId") Long courseId){
		CourseCommentEntity courseComment = courseCommentService.getById(courseId);

        return R.ok().put("courseComment", courseComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("course:coursecomment:save")
    public R save(@RequestBody CourseCommentEntity courseComment){
		courseCommentService.save(courseComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("course:coursecomment:update")
    public R update(@RequestBody CourseCommentEntity courseComment){
		courseCommentService.updateById(courseComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("course:coursecomment:delete")
    public R delete(@RequestBody Long[] courseIds){
		courseCommentService.removeByIds(Arrays.asList(courseIds));

        return R.ok();
    }

}
