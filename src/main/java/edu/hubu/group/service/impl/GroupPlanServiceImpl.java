package edu.hubu.group.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.group.dao.GroupPlanDao;
import edu.hubu.group.entity.GroupPlanEntity;
import edu.hubu.group.service.GroupPlanService;


@Service("groupPlanService")
public class GroupPlanServiceImpl extends ServiceImpl<GroupPlanDao, GroupPlanEntity> implements GroupPlanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupPlanEntity> page = this.page(
                new Query<GroupPlanEntity>().getPage(params),
                new QueryWrapper<GroupPlanEntity>()
        );

        return new PageUtils(page);
    }

}