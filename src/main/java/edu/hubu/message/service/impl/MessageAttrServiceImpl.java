package edu.hubu.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.UserHolder;
import edu.hubu.member.dto.UserDto;
import edu.hubu.message.dao.MessageAttrDao;
import edu.hubu.message.entity.MessageAttrEntity;
import edu.hubu.message.service.MessageAttrService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("messageAttrService")
public class MessageAttrServiceImpl extends ServiceImpl<MessageAttrDao, MessageAttrEntity> implements MessageAttrService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDto user = UserHolder.getUser();

        IPage<MessageAttrEntity> page = this.page(
                new Query<MessageAttrEntity>().getPage(params),
                new QueryWrapper<MessageAttrEntity>()
                        .eq("to", user.getId())
        );

        return new PageUtils(page);
    }

}