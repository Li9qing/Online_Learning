package edu.hubu.user.service;

import edu.hubu.user.entity.UserFavor;

import java.util.List;

public interface UserFavorService {
    public boolean addFavor(UserFavor uf);
    public boolean CHKdup(String userId,String folderId,String resourceId,String resourceType);
    public boolean CHKdup(Long userId,Long folderId,Long resourceId,Integer resourceType);
    public Integer DelMulti(List<Long> ids);
    public List<UserFavor> getFavors(String userId);
}
