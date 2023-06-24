package edu.hubu.group.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.group.entity.GroupMemberEntity;
import edu.hubu.group.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
@RestController
@RequestMapping("group/member")
public class GroupMemberController {
    @Autowired
    private GroupMemberService groupMemberService;

    /**
     * 列表
     */
    @GetMapping("/list/{groupId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long groupId) {
        PageUtils page = groupMemberService.queryPage(params, groupId);

        return R.ok().put("page", page);
    }

    /**
     * 申请加入群组
     */
    @PostMapping("/user/join/{groupId}")
    public R join(@PathVariable Long groupId) {

        return groupMemberService.join(groupId);
    }

    /**
     * 群组审核
     */
    @PutMapping("/audit/{groupId}/{userId}/{isAgree}")
    public R audit(@PathVariable Long groupId, @PathVariable Long userId, @PathVariable Boolean isAgree) {

        return groupMemberService.audit(groupId, userId, isAgree);
    }

    /**
     * 待审核列表
     */
    @GetMapping("/audit/list/{groupId}")
    public R auditList(@RequestParam Map<String, Object> params, @PathVariable Long groupId) {

        return groupMemberService.auditList(params, groupId);
    }

    /**
     * 学习时间排行榜
     */
    @GetMapping("/user/rank/{groupId}")
    public R rank(@RequestParam Map<String, Object> params, @PathVariable Long groupId) {

        return groupMemberService.queryPageByRank(params, groupId);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId) {
        GroupMemberEntity groupMember = groupMemberService.getById(userId);

        return R.ok().put("groupMember", groupMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GroupMemberEntity groupMember) {
        groupMemberService.save(groupMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GroupMemberEntity groupMember) {
        groupMemberService.updateById(groupMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] userIds) {
        groupMemberService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
