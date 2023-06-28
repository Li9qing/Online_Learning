package edu.hubu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.Query;
import edu.hubu.user.dao.UserDao;
import edu.hubu.user.dao.UserFavorDao;
import edu.hubu.user.entity.UserEntity;
import edu.hubu.user.entity.UserFavor;
import edu.hubu.user.entity.UserFolder;
import edu.hubu.user.service.UserFavorService;
import edu.hubu.user.service.UserService;
import edu.hubu.user.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.hubu.user.utils.WrapperUtils.getWrapperByUserId;

@Service
public class UserFavorServiceImpl extends ServiceImpl<UserFavorDao, UserFavor> implements UserFavorService {
    @Autowired
    UserFolderServiceImpl FOLD;
    public boolean addFolder(String userId,String folderName){
//        QueryWrapper<UserFavor> effect = new QueryWrapper<>();
//        Long ach;//因为数据库出毛病了所有只能自己随机id
//        Long ach1;
//        do{
//            ach = Long.valueOf(RandomUtil.getSixBitRandom());
//            ach1 = Long.valueOf(RandomUtil.getSixBitRandom());
//            effect.eq("folder_id",ach);
//            effect.eq("id",ach1);
//
//        }while (this.getOne(effect)!=null);
//        UserFavor e1 = UserFavor.makeFolder(Long.valueOf(userId),ach,ach1);
//;
//        return this.save(e1);
        return false;
    }

    @Override
    public boolean addFavor(UserFavor uf) {
        if(!FOLD.CHKdup(uf.getUserId(),uf.getFolderId())||
        this.CHKdup(uf.getUserId(), uf.getFolderId(),uf.getResourceId(),uf.getResourceType())){
            return false;
        }
        return this.addFavor(uf);
    }

    @Override
    public boolean CHKdup(String userId, String folderId, String resourceId, String resourceType) {
        return false;
    }

    @Override
    public boolean CHKdup(Long userId, Long folderId, Long resourceId, Integer resourceType) {
        return false;
    }

    @Override
    public Integer DelMulti(List<Long> ids) {
        return null;
    }

    @Override
    public List<UserFavor> getFavors(String userId) {
        QueryWrapper<UserFavor> q1 = getWrapperByUserId(UserFavor.class,userId);
        return this.list(q1);
    }
}