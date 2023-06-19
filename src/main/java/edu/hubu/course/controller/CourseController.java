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

import edu.hubu.course.entity.CourseEntity;
import edu.hubu.course.service.CourseService;
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
@RequestMapping("course/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("course:course:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("course:course:info")
    public R info(@PathVariable("id") Long id){
		CourseEntity course = courseService.getById(id);

        return R.ok().put("course", course);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("course:course:save")
    public R save(@RequestBody CourseEntity course){
		courseService.save(course);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("course:course:update")
    public R update(@RequestBody CourseEntity course){
		courseService.updateById(course);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("course:course:delete")
    public R delete(@RequestBody Long[] ids){
		courseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
