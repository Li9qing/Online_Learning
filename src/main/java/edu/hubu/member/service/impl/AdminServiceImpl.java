package edu.hubu.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.JwtUtils;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;
import edu.hubu.common.utils.R;
import edu.hubu.member.dao.AdminDao;
import edu.hubu.member.entity.AdminEntity;
import edu.hubu.member.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, AdminEntity> implements AdminService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminEntity> page = this.page(
                new Query<AdminEntity>().getPage(params),
                new QueryWrapper<AdminEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R login(String username, String password) {
        // 验证用户名密码
        AdminEntity admin = this.query().eq("username", username).eq("password", password).one();
        if (admin == null) {
            return R.error("用户名或密码错误");
        }
        // 登录成功
        String token = JwtUtils.createToken(admin.getUsername(), admin.getId(),
                2, admin.getNickName());

        return R.ok().put("Authorization", token);
    }

}