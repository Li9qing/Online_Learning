package edu.hubu.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.zxing.WriterException;
import edu.hubu.common.exception.CustomException;
import edu.hubu.common.utils.*;
import edu.hubu.exam.dao.ExamDao;
import edu.hubu.exam.entity.ExamEntity;
import edu.hubu.exam.service.ExamService;
import io.minio.UploadObjectArgs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service("examService")
public class ExamServiceImpl extends ServiceImpl<ExamDao, ExamEntity> implements ExamService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired MinioUtils minioUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExamEntity> page = this.page(
                new Query<ExamEntity>().getPage(params),
                new QueryWrapper<ExamEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ExamEntity release(ExamEntity e) throws IOException, WriterException {
        ExamEntity exam = this.getById(e.getId());
        if (exam.getShowStatus() == 1) {
            CustomException.cast("该测评已发布，不能重复发布");
        }
        // 设置发布状态
        exam.setShowStatus(1);
        // 设置测评过期时间
        exam.setExpireAt(e.getExpireAt());
        this.updateById(exam);

        // 生成二维码
        String uri = "/exam/" + e.getId().toString();
        String filePath = Constant.QR_PATH + e.getId() + ".png";
        QRCodeGenerator.generateQRCodeImage(uri, 350, 350, filePath);
        // 上传二维码到minio
        String objectName = "exam/" + e.getId().toString() + ".png";
        minioUtils.uploadFileToMinIO(filePath, objectName);
        // 删除本地二维码
        Files.deleteIfExists(Paths.get(filePath));

        // 将二维码路径写入redis
        redisTemplate.opsForSet().add(RedisKeys.EXAM_QRCODE + e.getId().toString(),
                objectName);
        redisTemplate.expire(RedisKeys.EXAM_QRCODE + e.getId().toString(),
                e.getExpireAt().getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        return exam;
    }




}