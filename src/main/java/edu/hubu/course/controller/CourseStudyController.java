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

import edu.hubu.course.entity.CourseStudyEntity;
import edu.hubu.course.service.CourseStudyService;
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
@RequestMapping("course/coursestudy")
public class CourseStudyController {
    @Autowired
    private CourseStudyService courseStudyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("course:coursestudy:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseStudyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    // @RequiresPermissions("course:coursestudy:info")
    public R info(@PathVariable("courseId") Integer courseId){
		CourseStudyEntity courseStudy = courseStudyService.getById(courseId);

        return R.ok().put("courseStudy", courseStudy);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("course:coursestudy:save")
    public R save(@RequestBody CourseStudyEntity courseStudy){
		courseStudyService.save(courseStudy);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("course:coursestudy:update")
    public R update(@RequestBody CourseStudyEntity courseStudy){
		courseStudyService.updateById(courseStudy);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("course:coursestudy:delete")
    public R delete(@RequestBody Integer[] courseIds){
		courseStudyService.removeByIds(Arrays.asList(courseIds));

        return R.ok();
    }

}
