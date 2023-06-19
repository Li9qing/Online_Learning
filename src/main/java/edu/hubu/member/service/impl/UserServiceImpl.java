package edu.hubu.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.JwtUtils;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.R;
import edu.hubu.member.dao.UserDao;
import edu.hubu.member.entity.UserEntity;
import edu.hubu.member.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void register(UserEntity user) {


    }

    @Override
    public R login(String username, String password) {
        // 验证账号密码
        UserEntity user = this.query().eq("username", username).eq("password", password).one();
        if (user == null) {
            return R.error("账号或密码不存在");
        }
        if (user.getStatus() == 1) {
            return R.error("账号已被封禁");
        }

        // 登录成功，加上token
        String token = JwtUtils.createToken(user.getUsername(), user.getId());

        return R.ok().put("token", token);
    }

}