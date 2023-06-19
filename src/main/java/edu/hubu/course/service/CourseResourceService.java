package edu.hubu.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.course.entity.CourseResourceEntity;

import java.util.Map;

/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:51:11
 */
public interface CourseResourceService extends IService<CourseResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

