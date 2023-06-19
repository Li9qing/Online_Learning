package edu.hubu.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.course.dao.CourseTeacherDao;
import edu.hubu.course.entity.CourseTeacherEntity;
import edu.hubu.course.service.CourseTeacherService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("courseTeacherService")
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherDao, CourseTeacherEntity> implements CourseTeacherService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseTeacherEntity> page = this.page(
                new Query<CourseTeacherEntity>().getPage(params),
                new QueryWrapper<CourseTeacherEntity>()
        );

        return new PageUtils(page);
    }

}