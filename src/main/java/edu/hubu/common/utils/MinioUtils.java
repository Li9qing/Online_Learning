package edu.hubu.common.utils;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MinioUtils for
 *
 * @Author yruns
 * @Version 2023/6/20
 */
@Slf4j
@Service
public class MinioUtils {

    @Autowired
    private MinioClient minioClient;

    // 存储普通文件
    @Value("${minio.bucket.mediafiles}")
    private String mediaFilesBucket;

    // 存储视频
    @Value("${minio.bucket.videofiles}")
    private String videoFilesBucket;


    // 上传文件到minio
    public void uploadFileToMinIO(String localFilePath, String objectName) {
        // 根据文件后缀名选择mimeType
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if (localFilePath.endsWith(".jpg") || localFilePath.endsWith(".jpeg")) {
            mimeType = MediaType.IMAGE_JPEG_VALUE;
        } else if (localFilePath.endsWith(".png")) {
            mimeType = MediaType.IMAGE_PNG_VALUE;
        } else if (localFilePath.endsWith(".mp4")) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // 根据文件后缀名选择bucket
        String bucket;
        if (localFilePath.endsWith(".mp4")) {
            bucket = videoFilesBucket;
        } else {
            bucket = mediaFilesBucket;
        }

        try {
            UploadObjectArgs objectArgs = UploadObjectArgs.builder()
                    .bucket(bucket)
                    .filename(localFilePath)
                    .object(objectName)
                    .contentType(mimeType)
                    .build();

            minioClient.uploadObject(objectArgs);

        } catch (Exception e) {
            log.error("上传文件出错, bucket:{}, objectName: {}, 错误信息: {}", bucket, objectName, e.getMessage());
        }

    }

    // 根据当前时间获取 年/月/日
    private String getDefaultFoldPath() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String folder = simpleDateFormat.format(new Date()).replace("-", "/") + "/";
        //   2023/03/18
        return folder;
    }


}
