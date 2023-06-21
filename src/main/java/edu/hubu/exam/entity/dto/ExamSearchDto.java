package edu.hubu.exam.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * ExamSearchDto for
 *
 * @Author yruns
 * @Version 2023/6/21
 */
@Data
public class ExamSearchDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 测评类别
     */
    private Integer type;
    /**
     * 排序方式
     */
    private Integer sortBy;
    /**
     * 搜索信息
     */
    private String Key;

}
