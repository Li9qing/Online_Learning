package edu.hubu.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.course.dao.CourseStudentDao;
import edu.hubu.course.entity.CourseStudentEntity;
import edu.hubu.course.service.CourseStudentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("courseStudentService")
public class CourseStudentServiceImpl extends ServiceImpl<CourseStudentDao, CourseStudentEntity> implements CourseStudentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseStudentEntity> page = this.page(
                new Query<CourseStudentEntity>().getPage(params),
                new QueryWrapper<CourseStudentEntity>()
        );

        return new PageUtils(page);
    }

}