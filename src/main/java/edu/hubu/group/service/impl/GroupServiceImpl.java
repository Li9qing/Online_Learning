package edu.hubu.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.MinioUtils;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.R;
import edu.hubu.group.dao.GroupDao;
import edu.hubu.group.entity.GroupEntity;
import edu.hubu.group.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service("groupService")
public class GroupServiceImpl extends ServiceImpl<GroupDao, GroupEntity> implements GroupService {

    @Autowired
    private MinioUtils minioUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupEntity> page = this.page(
                new Query<GroupEntity>().getPage(params),
                new QueryWrapper<GroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public R uploadAvatar(Long groupId, String filePath) {
        // 获取文件后缀
        String suffix = filePath.substring(filePath.lastIndexOf("."));
        // uuid生成文件名
        String objectName = UUID.randomUUID() + suffix;
        // 上传到minio
        minioUtils.uploadFileToMinIO(filePath, objectName);

        // 获取group
        GroupEntity byId = this.getById(groupId);
        String oldAvatar = byId.getAvatar();
        byId.setAvatar(objectName);


        try {
            // 删除本地临时文件
            Files.deleteIfExists(Paths.get(filePath));
            // minio删除旧头像
            if (oldAvatar != null) {
                minioUtils.deleteFileFromMinIO(oldAvatar);
            }

        } catch (Exception e) {
            log.info("文件删除失败");
        }

        // 将新头像url保存到数据库
        this.updateById(byId);

        return R.ok();
    }

    @Override
    public R queryPageByKey(Map<String, Object> params, String key) {
        IPage<GroupEntity> page = this.page(
                new Query<GroupEntity>().getPage(params),
                new QueryWrapper<GroupEntity>()
                        .like("description", key)
        );

        return R.ok().put("page", new PageUtils(page));
    }


}