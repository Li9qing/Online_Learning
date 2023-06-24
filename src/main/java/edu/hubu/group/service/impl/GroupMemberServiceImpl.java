package edu.hubu.group.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.*;
import edu.hubu.group.dao.GroupMemberDao;
import edu.hubu.group.entity.GroupMemberEntity;
import edu.hubu.group.service.GroupMemberService;
import edu.hubu.group.service.GroupService;
import edu.hubu.member.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import static edu.hubu.common.utils.RedisKeys.USER_INBOX;

@Slf4j
@Service("groupMemberService")
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberDao, GroupMemberEntity> implements GroupMemberService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long groupId) {
        IPage<GroupMemberEntity> page = this.page(
                new Query<GroupMemberEntity>().getPage(params),
                new QueryWrapper<GroupMemberEntity>()
                        .eq("group_id", groupId)
        );

        return new PageUtils(page);
    }

    @Override
    public R join(Long groupId) {
        // 获取当前用户
        UserDto user = UserHolder.getUser();
        // 查询当前状态
        GroupMemberEntity record = this.query().eq("user_id", user.getId())
                .eq("group_id", groupId).one();

        if (record == null) {
            // 记录不存在，添加记录，状态设置为待审核
            GroupMemberEntity entity = new GroupMemberEntity();
            entity.setGroupId(groupId);
            entity.setUserId(user.getId());
            entity.setCreateTime(new Date());
            entity.setStatus(0);

            this.save(entity);

            // 向群组负责人发送信息
            Long userId = groupService.getById(groupId).getUserId();
            redisTemplate.opsForZSet().add(USER_INBOX + userId.toString(), user.getId().toString(),
                    System.currentTimeMillis());

            return R.ok();
        }

        if (record.getStatus() == 0) {
            // 已经在审核，更新时间
            record.setCreateTime(new Date());
        } else if (record.getStatus() == 1) {
            // 已经拒绝，再次申请
            record.setStatus(0);
            record.setCreateTime(new Date());
        }

        // 更新数据库
        this.updateById(record);

        return R.ok();
    }

    @Override
    public R audit(Long groupId, Long userId, Boolean isAgree) {
        // 查询当前状态
        GroupMemberEntity record = this.query().eq("user_id", userId)
                .eq("group_id", groupId).one();

        if (record == null) {
            return R.error("当前记录不存在");
        }

        record.setStatus(isAgree ? 2 : 1);
        record.setJoinTime(new Date());
        record.setStudyTime(0);
        this.updateById(record);

        return R.ok();
    }

    @Override
    public R auditList(Map<String, Object> params, Long groupId) {
        IPage<GroupMemberEntity> page = this.page(
                new Query<GroupMemberEntity>().getPage(params),
                new QueryWrapper<GroupMemberEntity>()
                        .eq("group_id", groupId)
                        .eq("status", 0)
        );

        return R.ok().put("page", new PageUtils(page));
    }

    @Override
    public R queryPageByRank(Map<String, Object> params, Long groupId) {
        IPage<GroupMemberEntity> page = this.page(
                new Query<GroupMemberEntity>().getPage(params),
                new QueryWrapper<GroupMemberEntity>()
                        .eq("group_id", groupId)
                        .orderByDesc("study_time")
        );

        return R.ok().put("page", new PageUtils(page));
    }


}