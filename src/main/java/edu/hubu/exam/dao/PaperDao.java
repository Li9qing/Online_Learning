package edu.hubu.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hubu.exam.entity.PaperEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@Mapper
public interface PaperDao extends BaseMapper<PaperEntity> {

}
