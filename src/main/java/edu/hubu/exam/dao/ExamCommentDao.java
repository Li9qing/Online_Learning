package edu.hubu.exam.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hubu.exam.entity.ExamCommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@Mapper
public interface ExamCommentDao extends BaseMapper<ExamCommentEntity> {
}
