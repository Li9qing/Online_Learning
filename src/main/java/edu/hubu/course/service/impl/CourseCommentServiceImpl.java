package edu.hubu.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.course.dao.CourseCommentDao;
import edu.hubu.course.entity.CourseCommentEntity;
import edu.hubu.course.service.CourseCommentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("courseCommentService")
public class CourseCommentServiceImpl extends ServiceImpl<CourseCommentDao, CourseCommentEntity> implements CourseCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseCommentEntity> page = this.page(
                new Query<CourseCommentEntity>().getPage(params),
                new QueryWrapper<CourseCommentEntity>()
        );

        return new PageUtils(page);
    }

}