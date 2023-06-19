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

import edu.hubu.course.entity.CourseTeacherEntity;
import edu.hubu.course.service.CourseTeacherService;
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
@RequestMapping("course/courseteacher")
public class CourseTeacherController {
    @Autowired
    private CourseTeacherService courseTeacherService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("course:courseteacher:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseTeacherService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    // @RequiresPermissions("course:courseteacher:info")
    public R info(@PathVariable("courseId") Long courseId){
		CourseTeacherEntity courseTeacher = courseTeacherService.getById(courseId);

        return R.ok().put("courseTeacher", courseTeacher);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("course:courseteacher:save")
    public R save(@RequestBody CourseTeacherEntity courseTeacher){
		courseTeacherService.save(courseTeacher);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("course:courseteacher:update")
    public R update(@RequestBody CourseTeacherEntity courseTeacher){
		courseTeacherService.updateById(courseTeacher);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("course:courseteacher:delete")
    public R delete(@RequestBody Long[] courseIds){
		courseTeacherService.removeByIds(Arrays.asList(courseIds));

        return R.ok();
    }

}
