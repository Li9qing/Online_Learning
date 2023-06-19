package edu.hubu.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.message.entity.MessageAttrEntity;

import java.util.Map;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:55:45
 */
public interface MessageAttrService extends IService<MessageAttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

