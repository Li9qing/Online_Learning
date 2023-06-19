package edu.hubu.course.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.course.entity.CourseStudyEntity;
import edu.hubu.course.service.CourseStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
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
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = courseStudyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    public R info(@PathVariable("courseId") Integer courseId) {
        CourseStudyEntity courseStudy = courseStudyService.getById(courseId);

        return R.ok().put("courseStudy", courseStudy);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CourseStudyEntity courseStudy) {
        courseStudyService.save(courseStudy);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CourseStudyEntity courseStudy) {
        courseStudyService.updateById(courseStudy);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] courseIds) {
        courseStudyService.removeByIds(Arrays.asList(courseIds));

        return R.ok();
    }

}
