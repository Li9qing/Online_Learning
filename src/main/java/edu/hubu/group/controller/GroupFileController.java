package edu.hubu.group.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.group.entity.GroupFileEntity;
import edu.hubu.group.service.GroupFileService;
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
@RequestMapping("group/groupfile")
public class GroupFileController {
    @Autowired
    private GroupFileService groupFileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = groupFileService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fileId}")
    public R info(@PathVariable("fileId") Long fileId) {
        GroupFileEntity groupFile = groupFileService.getById(fileId);

        return R.ok().put("groupFile", groupFile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GroupFileEntity groupFile) {
        groupFileService.save(groupFile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GroupFileEntity groupFile) {
        groupFileService.updateById(groupFile);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] fileIds) {
        groupFileService.removeByIds(Arrays.asList(fileIds));

        return R.ok();
    }

}
