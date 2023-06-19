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

import edu.hubu.course.entity.CourseResourceEntity;
import edu.hubu.course.service.CourseResourceService;
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
@RequestMapping("course/courseresource")
public class CourseResourceController {
    @Autowired
    private CourseResourceService courseResourceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("course:courseresource:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseResourceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("course:courseresource:info")
    public R info(@PathVariable("id") Integer id){
		CourseResourceEntity courseResource = courseResourceService.getById(id);

        return R.ok().put("courseResource", courseResource);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("course:courseresource:save")
    public R save(@RequestBody CourseResourceEntity courseResource){
		courseResourceService.save(courseResource);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("course:courseresource:update")
    public R update(@RequestBody CourseResourceEntity courseResource){
		courseResourceService.updateById(courseResource);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("course:courseresource:delete")
    public R delete(@RequestBody Integer[] ids){
		courseResourceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
