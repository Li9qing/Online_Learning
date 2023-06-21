package edu.hubu.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.zxing.WriterException;
import edu.hubu.common.exception.CustomException;
import edu.hubu.common.utils.*;
import edu.hubu.exam.dao.ExamDao;
import edu.hubu.exam.dto.ExamSearchDto;
import edu.hubu.exam.entity.ExamEntity;
import edu.hubu.exam.service.ExamService;
import io.minio.UploadObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
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
        if (exam.getShowStatus() != null && exam.getShowStatus() == 1) {
            CustomException.cast("该测评已发布，不能重复发布");
        }
        // 设置发布状态
        exam.setShowStatus(1);
        // 设置测评过期时间
        exam.setExpireAt(e.getExpireAt());
        this.updateById(exam);

        // 生成二维码
        String uri = "/exam/" + exam.getId().toString();
        String filePath = Constant.QR_PATH + exam.getId() + ".png";
        QRCodeGenerator.generateQRCodeImage(uri, 350, 350, filePath);
        // 上传二维码到minio
        String objectName = "exam/" + exam.getId().toString() + ".png";
        minioUtils.uploadFileToMinIO(filePath, objectName);
        // 删除本地二维码
        Files.deleteIfExists(Paths.get(filePath));
        // todo 延时删除minio中的二维码

        // 将二维码路径写入redis
        redisTemplate.opsForValue().set(RedisKeys.EXAM_QRCODE + exam.getId().toString(),
                objectName);
        redisTemplate.expire(RedisKeys.EXAM_QRCODE + exam.getId().toString(),
                exam.getExpireAt().getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        return exam;
    }

    @Override
    public R getQRCode(Long id) {
        ExamEntity exam = this.getById(id);
        if (exam == null) {
            return R.error("该测评不存在");
        }

        if (exam.getShowStatus() == null || exam.getShowStatus() == 0) {
            return R.error("该测评尚未发布");
        }

        // 从redis中获取二维码路径
        String objectName = redisTemplate.opsForValue().get(RedisKeys.EXAM_QRCODE + id.toString());

        if (objectName == null) {
            return R.error("二维码已过期");
        }

        return R.ok().put("qrLink", objectName);
    }

    @Override
    public R search(Map<String, Object> params, ExamSearchDto examSearchDto) {
        String sortBy = examSearchDto.getSortBy() != null ?
                (examSearchDto.getSortBy() == 1 ? "create_time": "expire_at"): null;

        IPage<ExamEntity> page = this.page(
                new Query<ExamEntity>().getPage(params),
                new QueryWrapper<ExamEntity>()
                        .eq(examSearchDto.getType() != null, "type", examSearchDto.getType())
                        .eq("show_status", 1)
                        .like(examSearchDto.getKey() != null, "description", examSearchDto.getKey())
                        .orderBy(sortBy != null, false,sortBy)
        );

        return R.ok().put("page", new PageUtils(page));
    }


}