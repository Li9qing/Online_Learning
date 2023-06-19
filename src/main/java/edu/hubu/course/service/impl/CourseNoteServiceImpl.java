package edu.hubu.course.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.course.dao.CourseNoteDao;
import edu.hubu.course.entity.CourseNoteEntity;
import edu.hubu.course.service.CourseNoteService;


@Service("courseNoteService")
public class CourseNoteServiceImpl extends ServiceImpl<CourseNoteDao, CourseNoteEntity> implements CourseNoteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseNoteEntity> page = this.page(
                new Query<CourseNoteEntity>().getPage(params),
                new QueryWrapper<CourseNoteEntity>()
        );

        return new PageUtils(page);
    }

}