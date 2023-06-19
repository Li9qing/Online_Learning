package edu.hubu.group.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hubu.group.entity.GroupMemberEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
@Mapper
public interface GroupMemberDao extends BaseMapper<GroupMemberEntity> {

}
