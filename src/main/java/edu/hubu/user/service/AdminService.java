package edu.hubu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.user.entity.AdminDTO;
import edu.hubu.user.entity.AdminEntity;

import java.util.Map;

/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
public interface AdminService extends IService<AdminEntity> {

    PageUtils queryPage(Map<String, Object> params);
    String login(AdminEntity admin);


    AdminDTO getAdminInfo(String token);

    void logout(String token);
}

