package edu.hubu.group.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.group.entity.GroupLabelEntity;
import edu.hubu.group.service.GroupLabelService;
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
@RequestMapping("group/grouplabel")
public class GroupLabelController {
    @Autowired
    private GroupLabelService groupLabelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = groupLabelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{labelId}")
    public R info(@PathVariable("labelId") Long labelId) {
        GroupLabelEntity groupLabel = groupLabelService.getById(labelId);

        return R.ok().put("groupLabel", groupLabel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GroupLabelEntity groupLabel) {
        groupLabelService.save(groupLabel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GroupLabelEntity groupLabel) {
        groupLabelService.updateById(groupLabel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] labelIds) {
        groupLabelService.removeByIds(Arrays.asList(labelIds));

        return R.ok();
    }

}
