package edu.hubu.course.dao;

import edu.hubu.course.entity.CourseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:51:11
 */
@Mapper
public interface CourseDao extends BaseMapper<CourseEntity> {
	
}