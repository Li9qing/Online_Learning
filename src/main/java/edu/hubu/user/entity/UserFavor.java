package edu.hubu.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import edu.hubu.user.service.UserFavorService;

@TableName("user_favor")
public class UserFavor {

    private Long userId;
    private Long id;
    private String folderName;
    private Long folderId;
    private Long urlId;
    private String info;
    private String type;

    // Getters and Setters
    public static UserFavor makeFolder(Long userId,Long folderId,String folderName,Long id){
        UserFavor e1 = new UserFavor();
        e1.setFolderId(folderId);
        e1.setUserId(userId);
        e1.setId(id);
        e1.setFolderName(folderName);
        e1.setInfo("*");
        e1.setType("*");
        e1.setUrlId(Long.valueOf(0));
        return e1;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
