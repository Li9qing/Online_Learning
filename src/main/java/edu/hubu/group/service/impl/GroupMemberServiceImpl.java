package edu.hubu.group.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.group.dao.GroupMemberDao;
import edu.hubu.group.entity.GroupMemberEntity;
import edu.hubu.group.service.GroupMemberService;


@Service("groupMemberService")
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberDao, GroupMemberEntity> implements GroupMemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupMemberEntity> page = this.page(
                new Query<GroupMemberEntity>().getPage(params),
                new QueryWrapper<GroupMemberEntity>()
        );

        return new PageUtils(page);
    }

}