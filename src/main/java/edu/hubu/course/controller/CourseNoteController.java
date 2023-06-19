package edu.hubu.course.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.course.entity.CourseNoteEntity;
import edu.hubu.course.service.CourseNoteService;
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
@RequestMapping("course/coursenote")
public class CourseNoteController {
    @Autowired
    private CourseNoteService courseNoteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = courseNoteService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    public R info(@PathVariable("courseId") Long courseId) {
        CourseNoteEntity courseNote = courseNoteService.getById(courseId);

        return R.ok().put("courseNote", courseNote);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CourseNoteEntity courseNote) {
        courseNoteService.save(courseNote);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CourseNoteEntity courseNote) {
        courseNoteService.updateById(courseNote);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] courseIds) {
        courseNoteService.removeByIds(Arrays.asList(courseIds));

        return R.ok();
    }

}
