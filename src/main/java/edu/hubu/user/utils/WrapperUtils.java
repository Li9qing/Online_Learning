package edu.hubu.user.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.course.entity.CourseEntity;
import edu.hubu.course.entity.CourseLessonEntity;
import edu.hubu.course.entity.CourseNoteEntity;
import edu.hubu.course.service.impl.CourseLessonServiceImpl;
import edu.hubu.course.service.impl.CourseServiceImpl;
import edu.hubu.user.entity.UserEntity;
import edu.hubu.user.entity.UserNoteEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class WrapperUtils {
    public static <T> QueryWrapper<T> getWrapperByUserId(Class<T> clazz, String id) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        return wrapper;
    }


//    public static <T> IPage<T> getPage(Class<T> clazz, Map<String, Object> params){
//        QueryWrapper<T> e1 = new QueryWrapper<>();
//        if (UserEntity.class.equals(clazz)) {
//            e1.eq("teacher_access", params.get("teacher_access"));
//            if (params.containsKey("username")) e1.eq("username", params.get("username"));
//            if (params.containsKey("userId")) e1.eq("user_id", params.get("username"));
//        }else if(CourseNoteEntity.class.equals(clazz)){
//            e1.eq("user_id", params.get("userid"));
//        }
//
//
//    }
}
