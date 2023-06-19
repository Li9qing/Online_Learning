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

import edu.hubu.course.entity.CourseLessonEntity;
import edu.hubu.course.service.CourseLessonService;
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
@RequestMapping("course/courselesson")
public class CourseLessonController {
    @Autowired
    private CourseLessonService courseLessonService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("course:courselesson:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseLessonService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("course:courselesson:info")
    public R info(@PathVariable("id") Long id){
		CourseLessonEntity courseLesson = courseLessonService.getById(id);

        return R.ok().put("courseLesson", courseLesson);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("course:courselesson:save")
    public R save(@RequestBody CourseLessonEntity courseLesson){
		courseLessonService.save(courseLesson);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("course:courselesson:update")
    public R update(@RequestBody CourseLessonEntity courseLesson){
		courseLessonService.updateById(courseLesson);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("course:courselesson:delete")
    public R delete(@RequestBody Long[] ids){
		courseLessonService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
