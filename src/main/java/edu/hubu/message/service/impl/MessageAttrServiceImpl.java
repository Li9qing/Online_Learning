package edu.hubu.message.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.message.dao.MessageAttrDao;
import edu.hubu.message.entity.MessageAttrEntity;
import edu.hubu.message.service.MessageAttrService;


@Service("messageAttrService")
public class MessageAttrServiceImpl extends ServiceImpl<MessageAttrDao, MessageAttrEntity> implements MessageAttrService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MessageAttrEntity> page = this.page(
                new Query<MessageAttrEntity>().getPage(params),
                new QueryWrapper<MessageAttrEntity>()
        );

        return new PageUtils(page);
    }

}