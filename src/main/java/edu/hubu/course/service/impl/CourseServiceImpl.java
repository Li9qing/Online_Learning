package edu.hubu.course.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.course.dao.CourseDao;
import edu.hubu.course.entity.CourseEntity;
import edu.hubu.course.service.CourseService;


@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, CourseEntity> implements CourseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseEntity> page = this.page(
                new Query<CourseEntity>().getPage(params),
                new QueryWrapper<CourseEntity>()
        );

        return new PageUtils(page);
    }

}