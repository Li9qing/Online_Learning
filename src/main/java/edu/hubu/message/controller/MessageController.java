package edu.hubu.message.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.common.utils.UserHolder;
import edu.hubu.message.entity.MessageEntity;
import edu.hubu.message.entity.dto.CreateMessageDto;
import edu.hubu.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:55:45
 */
@RestController
@RequestMapping("message/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 获取消息列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = messageService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 用户获取消息列表
     */
    @GetMapping("/user/list")
    public R userList(@RequestParam Map<String, Object> params) {
        PageUtils page = messageService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 管理员创建消息
     */
    @PostMapping("/save")
    public R save(@RequestBody CreateMessageDto messageDto) {
        messageService.createMessage(messageDto);

        return R.ok();
    }

    /**
     * 用户获取消息条数
     */
    @GetMapping("/user/count")
    public R count() {

        return messageService.countMessage();
    }

    /**
     * 用户获取已读或未读消息条数
     */
    @GetMapping("/user/count/{isRead}")
    public R countIsReadOrNot(@PathVariable Boolean isRead) {

        return messageService.countMessageIsReadOrNot(isRead);
    }

    /**
     * 一键忽略所有消息
     */
    @GetMapping("/user/ignore")
    public R ignoreAll() {

        return messageService.ignoreAll();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MessageEntity message = messageService.getById(id);

        return R.ok().put("message", message);
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MessageEntity message) {
        messageService.updateById(message);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        messageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
