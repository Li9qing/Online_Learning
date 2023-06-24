package edu.hubu.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.MinioUtils;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.R;
import edu.hubu.group.dao.GroupFileDao;
import edu.hubu.group.entity.GroupFileEntity;
import edu.hubu.group.service.GroupFileService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


@Service("groupFileService")
public class GroupFileServiceImpl extends ServiceImpl<GroupFileDao, GroupFileEntity> implements GroupFileService {

    @Autowired
    private MinioUtils minioUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupFileEntity> page = this.page(
                new Query<GroupFileEntity>().getPage(params),
                new QueryWrapper<GroupFileEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R uploadFile(String filePath, GroupFileEntity groupFile) {
        // 获取文件后缀
        String suffix = filePath.substring(filePath.lastIndexOf("."));
        // uuid生成文件名
        String objectName = UUID.randomUUID() + suffix;
        // 上传到minio
        minioUtils.uploadFileToMinIO(filePath, objectName);

        groupFile.setUrl(objectName);
        groupFile.setUpdateTime(new Date());
        groupFile.setStatus(0); // 默认无法下载

        this.save(groupFile);

        return R.ok();
    }

    @Override
    public PageUtils queryPageById(Map<String, Object> params, Long groupId) {
        IPage<GroupFileEntity> page = this.page(
                new Query<GroupFileEntity>().getPage(params),
                new QueryWrapper<GroupFileEntity>()
                        .eq("group_id", groupId)
        );

        return new PageUtils(page);
    }


}