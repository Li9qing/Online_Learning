package edu.hubu.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hubu.member.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
