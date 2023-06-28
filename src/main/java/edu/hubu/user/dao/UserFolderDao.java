package edu.hubu.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hubu.user.entity.UserFolder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFolderDao extends BaseMapper<UserFolder> {
}
