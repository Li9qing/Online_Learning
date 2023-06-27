package edu.hubu.user.service;

public interface UserFavorService {
    public boolean addFolder(String userId,String folderName);
    public boolean addTo(String urlId,String type);
}
