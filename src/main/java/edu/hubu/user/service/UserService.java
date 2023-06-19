package edu.hubu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.user.entity.UserEntity;

import java.util.Map;

/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

