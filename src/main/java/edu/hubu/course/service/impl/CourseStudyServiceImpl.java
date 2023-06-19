package edu.hubu.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.course.dao.CourseStudyDao;
import edu.hubu.course.entity.CourseStudyEntity;
import edu.hubu.course.service.CourseStudyService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("courseStudyService")
public class CourseStudyServiceImpl extends ServiceImpl<CourseStudyDao, CourseStudyEntity> implements CourseStudyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseStudyEntity> page = this.page(
                new Query<CourseStudyEntity>().getPage(params),
                new QueryWrapper<CourseStudyEntity>()
        );

        return new PageUtils(page);
    }

}