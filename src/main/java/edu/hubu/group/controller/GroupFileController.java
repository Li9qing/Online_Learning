package edu.hubu.group.controller;

import edu.hubu.common.utils.MinioUtils;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.group.entity.GroupFileEntity;
import edu.hubu.group.service.GroupFileService;
import io.minio.errors.MinioException;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;


/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
@RestController
@RequestMapping("group/file")
public class GroupFileController {
    @Autowired
    private GroupFileService groupFileService;


    @Autowired
    private MinioUtils minioUtils;


    /**
     * 上传资料
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R upload(@RequestPart("filedata") MultipartFile filedata,
                    @RequestParam Long groupId) throws IOException {


        // 获取文件名（不带后缀）
        String filename = Objects.requireNonNull(filedata.getOriginalFilename())
                .substring(0, filedata.getOriginalFilename().lastIndexOf("."));

        GroupFileEntity groupFileEntity = new GroupFileEntity();
        groupFileEntity.setGroupId(groupId);
        groupFileEntity.setFileName(filename);

        // 获取文件后缀
        String suffix = Objects.requireNonNull(filedata.getOriginalFilename())
                .substring(filedata.getOriginalFilename().lastIndexOf("."));

        // 创建一个临时文件
        File minio = File.createTempFile("minio", suffix);
        filedata.transferTo(minio);

        String filePath = minio.getAbsolutePath();

        return groupFileService.uploadFile(filePath, groupFileEntity);
    }

    /**
     * 下载资料
     */
    @GetMapping("/user/download/{fileId}")
    public R getFile(HttpServletResponse response, @PathVariable String fileId) {
        try {
            GroupFileEntity groupFile = groupFileService.getById(fileId);
            if (groupFile.getStatus() == 0) {
                return R.error("不允许下载该文件");
            }

            InputStream inputStream = null;
            try {
                inputStream = minioUtils.downloadFileFromMinio(groupFile.getUrl());
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }

            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + groupFile.getFileName() + "\"");

            // 返回响应对象
            return R.ok().put("file", ResponseEntity.ok()
                    .contentLength(inputStream.available())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(inputStream)));
        } catch (IOException | NoSuchAlgorithmException | MinioException e) {
            e.printStackTrace();
            return R.error("下载失败");
        }
    }

    /**
     * 查看资料列表
     */
    @RequestMapping("/user/list/{groupId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long groupId) {
        PageUtils page = groupFileService.queryPageById(params, groupId);

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
