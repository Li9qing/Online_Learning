package edu.hubu.message.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * CreateMessageDto for
 *
 * @Author yruns
 * @Version 2023/6/24
 */
@Data
public class CreateMessageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private Integer type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 接收方
     */
    private Long recipient;
}
