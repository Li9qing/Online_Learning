package edu.hubu.exam.controller;

import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;
import edu.hubu.exam.dto.ManualReviewDto;
import edu.hubu.exam.entity.ScoreEntity;
import edu.hubu.exam.service.ScoreService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@RestController
@RequestMapping("exam/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;


    /**
     * 自动批阅客观题 type=1
     */
    @GetMapping("/review/{examId}")
    public R autoReview(@PathVariable Long examId) {

        scoreService.autoReview(examId);
        return R.ok();
    }

    /**
     * 手动批阅主观题 type=2
     */
    @PostMapping("/review")
    public R manualReview(@RequestBody ManualReviewDto manualReviewDto) {

        return scoreService.manualReview(manualReviewDto);
    }

    /**
     * 提交测评结果生成最终分数
     */
    @PostMapping("/submit/{examId}/{userId}")
    public R submitExam(@PathVariable Long examId, @PathVariable Long userId) {

        return scoreService.submitExam(examId, userId);
    }


    /**
     * 查看通过测评的用户列表
     */
    @GetMapping("/list/passed/{examId}")
    public R listPassed(@RequestParam Map<String, Object> params, @PathVariable Long examId) {
        PageUtils page = scoreService.listPassedPage(params, examId);

        return R.ok().put("page", page);
    }

    /**
     * 查看未通过测评的用户列表
     */
    @GetMapping("/list/failed/{examId}")
    public R listFailed(@RequestParam Map<String, Object> params, @PathVariable Long examId) {
        PageUtils page = scoreService.listFailedPage(params, examId);

        return R.ok().put("page", page);
    }

    /**
     * 查看测评的所有用户列表
     */
    @GetMapping("/list/{examId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long examId) {
        PageUtils page = scoreService.listPage(params, examId);

        return R.ok().put("page", page);
    }

    /**
     * 下载测评的所有用户列表
     */
    @GetMapping("/list/download")
    public void download(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=users.xlsx");

        // 创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 写入数据
        XSSFSheet sheet = workbook.createSheet("用户测评信息");
        // 设置表头
        XSSFRow head = sheet.createRow(0);
        head.createCell(0).setCellValue("用户ID");
        head.createCell(1).setCellValue("分数");
        head.createCell(2).setCellValue("开始时间");
        head.createCell(3).setCellValue("结束时间");

        List<ScoreEntity> entities = scoreService.list();  // 获取用户数据
        int rowNum = 0;
        for (ScoreEntity entity: entities) {
            XSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entity.getUserId());
            row.createCell(1).setCellValue(entity.getScore());
            row.createCell(2).setCellValue(entity.getStartTime());
            row.createCell(3).setCellValue(entity.getFinishTime());
        }

        // 将Excel文件写入响应输出流
        workbook.write(response.getOutputStream());

        // 关闭工作簿
        workbook.close();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{examId}")
    public R info(@PathVariable("examId") Long examId) {
        ScoreEntity score = scoreService.getById(examId);

        return R.ok().put("score", score);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ScoreEntity score) {
        scoreService.save(score);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ScoreEntity score) {
        scoreService.updateById(score);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] examIds) {
        scoreService.removeByIds(Arrays.asList(examIds));

        return R.ok();
    }

}
