package edu.hubu.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user_folder")
@Data
public class UserFolder {
    private Long folderId;
    private Long userId;
    private String folderName;
    private Integer count;
    private Integer type;

    // 省略构造方法、Getter 和 Setter 方法
}
