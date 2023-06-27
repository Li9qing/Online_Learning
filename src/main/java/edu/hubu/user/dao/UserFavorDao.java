package edu.hubu.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hubu.user.entity.UserEntity;
import edu.hubu.user.entity.UserFavor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFavorDao extends BaseMapper<UserFavor>  {
}
