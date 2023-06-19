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

import edu.hubu.course.entity.CourseStudentEntity;
import edu.hubu.course.service.CourseStudentService;
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
@RequestMapping("course/coursestudent")
public class CourseStudentController {
    @Autowired
    private CourseStudentService courseStudentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("course:coursestudent:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseStudentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    // @RequiresPermissions("course:coursestudent:info")
    public R info(@PathVariable("courseId") Long courseId){
		CourseStudentEntity courseStudent = courseStudentService.getById(courseId);

        return R.ok().put("courseStudent", courseStudent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("course:coursestudent:save")
    public R save(@RequestBody CourseStudentEntity courseStudent){
		courseStudentService.save(courseStudent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("course:coursestudent:update")
    public R update(@RequestBody CourseStudentEntity courseStudent){
		courseStudentService.updateById(courseStudent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("course:coursestudent:delete")
    public R delete(@RequestBody Long[] courseIds){
		courseStudentService.removeByIds(Arrays.asList(courseIds));

        return R.ok();
    }

}
