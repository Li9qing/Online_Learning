package edu.hubu.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.group.dao.ForumCommentDao;
import edu.hubu.group.entity.ForumCommentEntity;
import edu.hubu.group.service.ForumCommentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("forumCommentService")
public class ForumCommentServiceImpl extends ServiceImpl<ForumCommentDao, ForumCommentEntity> implements ForumCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ForumCommentEntity> page = this.page(
                new Query<ForumCommentEntity>().getPage(params),
                new QueryWrapper<ForumCommentEntity>()
        );

        return new PageUtils(page);
    }

}