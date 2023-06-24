package edu.hubu.common.utils;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    // 在minio中删除文件
    public void deleteFileFromMinIO(String objectName) {
        // 根据文件后缀名选择bucket
        String bucket;
        if (objectName.endsWith(".mp4")) {
            bucket = videoFilesBucket;
        } else {
            bucket = mediaFilesBucket;
        }
        try {
            // 删除文件的信息
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build();

            minioClient.removeObject(removeObjectArgs);
        } catch (Exception e) {
            log.error("删除文件出错, bucket:{}, objectName: {}, 错误信息: {}", bucket, objectName, e.getMessage());
        }
    }

    // 根据当前时间获取 年/月/日
    private String getDefaultFoldPath() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String folder = simpleDateFormat.format(new Date()).replace("-", "/") + "/";
        //   2023/03/18
        return folder;
    }


    public InputStream downloadFileFromMinio(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        // 根据文件后缀名选择bucket
        String bucket;
        if (objectName.endsWith(".mp4")) {
            bucket = videoFilesBucket;
        } else {
            bucket = mediaFilesBucket;
        }

        // 下载文件的信息
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .build();

        // 获取文件
        FilterInputStream filterInputStream = minioClient.getObject(getObjectArgs);
        // 向前端输出文件
        return filterInputStream;
    }
}
