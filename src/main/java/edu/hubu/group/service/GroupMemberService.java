package edu.hubu.group.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.group.entity.GroupMemberEntity;

import java.util.Map;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
public interface GroupMemberService extends IService<GroupMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

