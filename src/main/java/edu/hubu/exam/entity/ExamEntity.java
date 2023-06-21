package edu.hubu.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@Data
@TableName("exam")
public class ExamEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 试卷id
     */
    private Long paperId;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 测试描述
     */
    private String description;
    /**
     * 通过分数
     */
    private Integer passScore;
    /**
     * 限时分钟数
     */
    private Integer timeLimit;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expireAt;
    /**
     * 发布状态
     */
    private Integer showStatus;
    /**
     * 是否删除
     */
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;

}
