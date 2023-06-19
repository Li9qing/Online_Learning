package edu.hubu.group.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.group.dao.GroupLabelDao;
import edu.hubu.group.entity.GroupLabelEntity;
import edu.hubu.group.service.GroupLabelService;


@Service("groupLabelService")
public class GroupLabelServiceImpl extends ServiceImpl<GroupLabelDao, GroupLabelEntity> implements GroupLabelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupLabelEntity> page = this.page(
                new Query<GroupLabelEntity>().getPage(params),
                new QueryWrapper<GroupLabelEntity>()
        );

        return new PageUtils(page);
    }

}