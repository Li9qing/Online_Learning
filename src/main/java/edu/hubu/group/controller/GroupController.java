package edu.hubu.group.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.common.utils.UserHolder;
import edu.hubu.group.entity.GroupEntity;
import edu.hubu.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;


/**
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
     * 创建群组
     */
    @PostMapping("/save")
    public R save(@RequestBody GroupEntity group) {
        // 设置创建人
        group.setUserId(UserHolder.getUser().getId());
        groupService.save(group);

        return R.ok();
    }

    /**
     * 修改群组信息
     */
    @PutMapping("/update")
    public R update(@RequestBody GroupEntity group) {
        // 设置创建人
        groupService.updateById(group);

        return R.ok();
    }

    /**
     * 头像上传
     */
    @PostMapping(value = "/upload/avatar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R upload(@RequestPart("filedata") MultipartFile filedata, @PathVariable Long id) throws IOException {

        // 获取文件后缀
        String suffix = Objects.requireNonNull(filedata.getOriginalFilename())
                .substring(filedata.getOriginalFilename().lastIndexOf("."));

        // 创建一个临时文件
        File minio = File.createTempFile("minio", suffix);
        filedata.transferTo(minio);

        String filePath = minio.getAbsolutePath();

        return groupService.uploadAvatar(id, filePath);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = groupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        GroupEntity group = groupService.getById(id);

        return R.ok().put("group", group);
    }



    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        groupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
