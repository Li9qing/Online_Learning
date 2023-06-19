package edu.hubu.group.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 20:32:03
 */
@Data
@TableName("group_member")
public class GroupMemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 组内用户id
     */
    @TableId
    private Long userId;
    /**
     * 入组时间
     */
    private Date joinTime;
    /**
     * 群组id
     */
    private Long groupId;
    /**
     * 总学习时长
     */
    private Integer studyTime;

}
