package edu.hubu.group.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hubu.group.entity.GroupMemberEntity;
import edu.hubu.group.service.GroupMemberService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;



/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
@RestController
@RequestMapping("group/groupmember")
public class GroupMemberController {
    @Autowired
    private GroupMemberService groupMemberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("group:groupmember:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = groupMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    // @RequiresPermissions("group:groupmember:info")
    public R info(@PathVariable("userId") Long userId){
		GroupMemberEntity groupMember = groupMemberService.getById(userId);

        return R.ok().put("groupMember", groupMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("group:groupmember:save")
    public R save(@RequestBody GroupMemberEntity groupMember){
		groupMemberService.save(groupMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("group:groupmember:update")
    public R update(@RequestBody GroupMemberEntity groupMember){
		groupMemberService.updateById(groupMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("group:groupmember:delete")
    public R delete(@RequestBody Long[] userIds){
		groupMemberService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
