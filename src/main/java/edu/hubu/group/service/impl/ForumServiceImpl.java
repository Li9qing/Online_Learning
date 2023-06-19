package edu.hubu.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.group.dao.ForumDao;
import edu.hubu.group.entity.ForumEntity;
import edu.hubu.group.service.ForumService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("forumService")
public class ForumServiceImpl extends ServiceImpl<ForumDao, ForumEntity> implements ForumService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ForumEntity> page = this.page(
                new Query<ForumEntity>().getPage(params),
                new QueryWrapper<ForumEntity>()
        );

        return new PageUtils(page);
    }

}