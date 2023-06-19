package edu.hubu.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.course.dao.CourseLessonDao;
import edu.hubu.course.entity.CourseLessonEntity;
import edu.hubu.course.service.CourseLessonService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("courseLessonService")
public class CourseLessonServiceImpl extends ServiceImpl<CourseLessonDao, CourseLessonEntity> implements CourseLessonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseLessonEntity> page = this.page(
                new Query<CourseLessonEntity>().getPage(params),
                new QueryWrapper<CourseLessonEntity>()
        );

        return new PageUtils(page);
    }

}