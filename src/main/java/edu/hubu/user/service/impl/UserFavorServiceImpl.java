package edu.hubu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.Query;
import edu.hubu.user.dao.UserDao;
import edu.hubu.user.dao.UserFavorDao;
import edu.hubu.user.entity.UserEntity;
import edu.hubu.user.entity.UserFavor;
import edu.hubu.user.service.UserFavorService;
import edu.hubu.user.service.UserService;
import edu.hubu.user.utils.RandomUtil;

public class UserFavorServiceImpl extends ServiceImpl<UserFavorDao, UserFavor> implements UserFavorService {
    public boolean addFolder(String userId,String folderName){
        QueryWrapper<UserFavor> effect = new QueryWrapper<>();
        Long ach;//因为数据库出毛病了所有只能自己随机id
        Long ach1;
        do{
            ach = Long.valueOf(RandomUtil.getSixBitRandom());
            ach1 = Long.valueOf(RandomUtil.getSixBitRandom());
            effect.eq("folder_id",ach);
            effect.eq("id",ach1);

        }while (this.getOne(effect)!=null);
        UserFavor e1 = UserFavor.makeFolder(Long.valueOf(userId),ach,folderName,ach1);
;
        return this.save(e1);
    }

    @Override
    public boolean addTo(String urlId,String type) {

        return false;
    }
}
