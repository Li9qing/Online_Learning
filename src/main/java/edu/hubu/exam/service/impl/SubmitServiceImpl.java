package edu.hubu.exam.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.exam.dao.SubmitDao;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.exam.service.SubmitService;


@Service("submitService")
public class SubmitServiceImpl extends ServiceImpl<SubmitDao, SubmitEntity> implements SubmitService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SubmitEntity> page = this.page(
                new Query<SubmitEntity>().getPage(params),
                new QueryWrapper<SubmitEntity>()
        );

        return new PageUtils(page);
    }

}