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

import edu.hubu.group.entity.GroupEntity;
import edu.hubu.group.service.GroupService;
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
@RequestMapping("group/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("group:group:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = groupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("group:group:info")
    public R info(@PathVariable("id") Long id){
		GroupEntity group = groupService.getById(id);

        return R.ok().put("group", group);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("group:group:save")
    public R save(@RequestBody GroupEntity group){
		groupService.save(group);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("group:group:update")
    public R update(@RequestBody GroupEntity group){
		groupService.updateById(group);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("group:group:delete")
    public R delete(@RequestBody Long[] ids){
		groupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
