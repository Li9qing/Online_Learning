package edu.hubu.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.group.dao.GroupFileDao;
import edu.hubu.group.entity.GroupFileEntity;
import edu.hubu.group.service.GroupFileService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("groupFileService")
public class GroupFileServiceImpl extends ServiceImpl<GroupFileDao, GroupFileEntity> implements GroupFileService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupFileEntity> page = this.page(
                new Query<GroupFileEntity>().getPage(params),
                new QueryWrapper<GroupFileEntity>()
        );

        return new PageUtils(page);
    }

}