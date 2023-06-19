package edu.hubu.group.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
@Data
@TableName("group")
public class GroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 群组名称
     */
    private String name;
    /**
     * 群组描述
     */
    private String description;
    /**
     * 群组头像
     */
    private String avatar;
    /**
     * 组长id
     */
    private Long userId;

}
