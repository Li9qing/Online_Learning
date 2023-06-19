package edu.hubu.message.dao;

import edu.hubu.message.entity.MessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:55:45
 */
@Mapper
public interface MessageDao extends BaseMapper<MessageEntity> {
	
}
