package edu.hubu.user.service;

import edu.hubu.user.entity.UserFolder;

import java.util.List;

public interface UserFolderService {
    public boolean Create(String userId);

    public boolean Create(String userId,String folderName);
    public Integer updFolders(String userId,String folderId);
    public Integer delFolder(String userId,String folderId);
    public boolean CHKdup(Long userId,Long folderId);
    public List<UserFolder> getFolders(String userId);

    public boolean CHKdup(String userId, String folderName);
}
