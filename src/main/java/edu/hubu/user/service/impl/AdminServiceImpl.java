package edu.hubu.user.service.impl;

import com.alibaba.fastjson.JSON;
import edu.hubu.user.entity.AdminDTO;
import edu.hubu.user.utils.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.user.dao.AdminDao;
import edu.hubu.user.entity.AdminEntity;
import edu.hubu.user.service.AdminService;

import javax.annotation.Resource;


@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, AdminEntity> implements AdminService {




    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public String login(AdminEntity admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();
        QueryWrapper<AdminEntity> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        AdminEntity adminDetails = this.getOne(userQueryWrapper);
//        if (adminDetails != null && !MD5.encrypt(password).equals(adminDetails.getPassword()))
//            throw new EduException("用户名或密码错误!");
        if (adminDetails == null){

            return "err";
        }
//        assert adminDetails != null;
        String jwtToken = JwtUtils.getJwtToken(adminDetails.getId().toString(), adminDetails.getUsername());
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(adminDetails, adminDTO);
        // 加入到redis中
        stringRedisTemplate.opsForValue().set(jwtToken, JSON.toJSONString(adminDTO), 1, TimeUnit.DAYS);
        return jwtToken;
    }
    @Override
    public AdminDTO getAdminInfo(String token) {
        String userJson = stringRedisTemplate.opsForValue().get(token);
        return JSON.parseObject(userJson, AdminDTO.class);
    }


    @Override
    public void logout(String token) {
        // 从redis中将token移除
        stringRedisTemplate.delete(token);
    }
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminEntity> page = this.page(
                new Query<AdminEntity>().getPage(params),
                new QueryWrapper<AdminEntity>()
        );

        return new PageUtils(page);
    }

}