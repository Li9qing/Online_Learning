package edu.hubu.course.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.course.entity.CourseLessonEntity;
import edu.hubu.course.service.CourseLessonService;
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
@RequestMapping("course/courselesson")
public class CourseLessonController {
    @Autowired
    private CourseLessonService courseLessonService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = courseLessonService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CourseLessonEntity courseLesson = courseLessonService.getById(id);

        return R.ok().put("courseLesson", courseLesson);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CourseLessonEntity courseLesson) {
        courseLessonService.save(courseLesson);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CourseLessonEntity courseLesson) {
        courseLessonService.updateById(courseLesson);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        courseLessonService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
