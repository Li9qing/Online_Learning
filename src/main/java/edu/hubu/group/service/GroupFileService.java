package edu.hubu.group.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.group.entity.GroupFileEntity;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
public interface GroupFileService extends IService<GroupFileEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R uploadFile(String filePath, GroupFileEntity groupFile);

    PageUtils queryPageById(Map<String, Object> params, Long groupId);

}

