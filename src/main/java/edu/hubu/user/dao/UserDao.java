package edu.hubu.user.dao;

import edu.hubu.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	@Delete("delete from user WHERE id = #{id}")
    int delDATAbyid(String id);
}
