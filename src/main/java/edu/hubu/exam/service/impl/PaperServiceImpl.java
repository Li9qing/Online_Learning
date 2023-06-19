package edu.hubu.exam.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.exam.dao.PaperDao;
import edu.hubu.exam.entity.PaperEntity;
import edu.hubu.exam.service.PaperService;


@Service("paperService")
public class PaperServiceImpl extends ServiceImpl<PaperDao, PaperEntity> implements PaperService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaperEntity> page = this.page(
                new Query<PaperEntity>().getPage(params),
                new QueryWrapper<PaperEntity>()
        );

        return new PageUtils(page);
    }

}