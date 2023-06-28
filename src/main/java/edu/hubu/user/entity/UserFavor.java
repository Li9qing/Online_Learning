package edu.hubu.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import edu.hubu.user.service.UserFavorService;
import lombok.Data;

@TableName("user_favor")
@Data
public class UserFavor {

    private Long userId;
    private Long id;
    private Long folderId;
    private Long resourceId;
    private String info;
    private Integer resourceType;



}
