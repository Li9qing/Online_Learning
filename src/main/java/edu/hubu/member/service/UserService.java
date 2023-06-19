package edu.hubu.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.member.entity.UserEntity;

import java.util.Map;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void register(UserEntity user);

    R login(String username, String password);
}

