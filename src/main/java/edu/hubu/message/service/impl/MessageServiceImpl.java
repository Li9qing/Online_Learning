package edu.hubu.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.R;
import edu.hubu.common.utils.UserHolder;
import edu.hubu.member.dto.UserDto;
import edu.hubu.message.dao.MessageDao;
import edu.hubu.message.entity.MessageAttrEntity;
import edu.hubu.message.entity.MessageEntity;
import edu.hubu.message.entity.dto.CreateMessageDto;
import edu.hubu.message.service.MessageAttrService;
import edu.hubu.message.service.MessageService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {

    @Autowired
    private MessageAttrService messageAttrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDto user = UserHolder.getUser();

        // 获取所有收件人为自己的信息
        List<MessageAttrEntity> toList = messageAttrService.query().eq("recipient", user.getId()).list();
        ArrayList<Long> ids = new ArrayList<>();
        toList.forEach(item -> {
            ids.add(item.getMessageId());
        });

        IPage<MessageEntity> page = this.page(
                new Query<MessageEntity>().getPage(params),
                new QueryWrapper<MessageEntity>()
                        .in("id", ids)
        );

        return new PageUtils(page);
    }

    @Override
    public void createMessage(CreateMessageDto messageDto) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setType(messageDto.getType());
        messageEntity.setContent(messageDto.getContent());
        this.save(messageEntity);

        MessageAttrEntity messageAttrEntity = new MessageAttrEntity();
        messageAttrEntity.setSender(UserHolder.getUser().getId());
        messageAttrEntity.setRecipient(messageDto.getRecipient());
        messageAttrEntity.setMessageId(messageEntity.getId());
        messageAttrEntity.setIsRead(0);
        messageAttrService.save(messageAttrEntity);
    }

    @Override
    public R countMessage() {

        int count = messageAttrService.query()
                .eq("recipient", UserHolder.getUser().getId())
                .count();
        return R.ok().put("count", count);
    }

    @Override
    public R countMessageIsReadOrNot(Boolean isRead) {

        int count = messageAttrService.query()
                .eq("is_read", isRead ? 1: 0)
                .eq("recipient", UserHolder.getUser().getId())
                .count();
        return R.ok().put("count", count);
    }

    @Override
    public R ignoreAll() {
        // update message_attr set is_read = 1 where recipient = ?;
        messageAttrService.update().eq("recipient", UserHolder.getUser().getId())
                .set("is_read", 1).update();

        return R.ok();
    }

}