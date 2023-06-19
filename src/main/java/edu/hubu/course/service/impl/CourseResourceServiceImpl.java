package edu.hubu.course.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.course.dao.CourseResourceDao;
import edu.hubu.course.entity.CourseResourceEntity;
import edu.hubu.course.service.CourseResourceService;


@Service("courseResourceService")
public class CourseResourceServiceImpl extends ServiceImpl<CourseResourceDao, CourseResourceEntity> implements CourseResourceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseResourceEntity> page = this.page(
                new Query<CourseResourceEntity>().getPage(params),
                new QueryWrapper<CourseResourceEntity>()
        );

        return new PageUtils(page);
    }

}