package edu.hubu.message.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:55:45
 */
@Data
@TableName("message_attr")
public class MessageAttrEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long messageId;
    /**
     * 发送方
     */
    // 设置数据库字段名
    private Long sender;
    /**
     * 接收方
     */
    private Long recipient;
    /**
     * 是否已读
     */
    private Integer isRead;

}
