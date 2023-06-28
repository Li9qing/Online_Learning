package edu.hubu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.user.dao.UserFolderDao;
import edu.hubu.user.entity.UserFavor;
import edu.hubu.user.entity.UserFolder;
import edu.hubu.user.service.UserFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.hubu.user.utils.WrapperUtils.getWrapperByUserId;

@Service
public class UserFolderServiceImpl extends ServiceImpl<UserFolderDao, UserFolder> implements UserFolderService {
    @Autowired
    UserFavorServiceImpl FAVOR;


    @Override
    public boolean Create(String userId) {
        return false;
    }

    @Override
    public boolean Create(String userId, String folderName) {
        if(this.CHKdup(userId,folderName))return false;
        UserFolder fd = new UserFolder();
        return this.save(fd);
    }

    @Override
    public Integer updFolders(String userId, String folderId) {
        return null;
    }

    @Override
    public Integer delFolder(String userId, String folderId) {
        return null;
    }

    @Override
    public boolean CHKdup(Long userId, Long folderId) {
        QueryWrapper<UserFolder> chk = new QueryWrapper<>();
        chk.eq("user_id",userId);
        chk.eq("folder_id",folderId);
        return this.getOne(chk)!=null;
    }

    @Override
    public List<UserFolder> getFolders(String userId) {
        QueryWrapper<UserFolder> q1 = getWrapperByUserId(UserFolder.class,userId);
        return this.list(q1);
    }


    @Override
    public boolean CHKdup(String userId, String folderName) {
        QueryWrapper<UserFolder> chk = new QueryWrapper<>();
        chk.eq("user_id",userId);
        chk.eq("folder_name",folderName);
        return this.getOne(chk)!=null;
    }
}
