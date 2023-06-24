package edu.hubu.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.message.entity.MessageEntity;
import edu.hubu.message.entity.dto.CreateMessageDto;

import java.util.Map;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:55:45
 */
public interface MessageService extends IService<MessageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void createMessage(CreateMessageDto messageDto);

    R countMessage();

    R countMessageIsReadOrNot(Boolean isRead);

    R ignoreAll();
}

