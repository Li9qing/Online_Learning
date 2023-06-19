package edu.hubu.message.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.hubu.message.entity.MessageAttrEntity;
import edu.hubu.message.service.MessageAttrService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;



/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:55:45
 */
@RestController
@RequestMapping("message/messageattr")
public class MessageAttrController {
    @Autowired
    private MessageAttrService messageAttrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("message:messageattr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = messageAttrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{messageId}")
    // @RequiresPermissions("message:messageattr:info")
    public R info(@PathVariable("messageId") Long messageId){
		MessageAttrEntity messageAttr = messageAttrService.getById(messageId);

        return R.ok().put("messageAttr", messageAttr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("message:messageattr:save")
    public R save(@RequestBody MessageAttrEntity messageAttr){
		messageAttrService.save(messageAttr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("message:messageattr:update")
    public R update(@RequestBody MessageAttrEntity messageAttr){
		messageAttrService.updateById(messageAttr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("message:messageattr:delete")
    public R delete(@RequestBody Long[] messageIds){
		messageAttrService.removeByIds(Arrays.asList(messageIds));

        return R.ok();
    }

}
