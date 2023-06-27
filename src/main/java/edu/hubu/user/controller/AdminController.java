package edu.hubu.user.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import edu.hubu.user.entity.AdminDTO;
import edu.hubu.user.entity.UserDTO;
import edu.hubu.user.entity.UserEntity;
import edu.hubu.user.interceptor.Login;
import edu.hubu.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.hubu.user.entity.AdminEntity;
import edu.hubu.user.service.AdminService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.R;

import static edu.hubu.user.utils.Result.BOOLresult;
import static edu.hubu.user.utils.Result.EMPTYdata;


/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@RestController

@RequestMapping("user/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody AdminEntity admin) {
        System.out.println("get Post");
        System.out.println(admin.toString());
        String token = adminService.login(admin);
        if(token.equals("err")){
            return R.error(20001,"no such user!");
        }
        return R.ok().put("token", token);
    }


    @RequestMapping("/ban")
    // @RequiresPermissions("exam:user:delete")
    public R delete(@RequestBody String id){
        if(id == null)return EMPTYdata();
        userService.removeById(id);

        return R.ok();
    }
    @RequestMapping("/BatchDel")
    public R BatchDel(@RequestBody Long [] ids){
        if(ids.length==0)return EMPTYdata();
        boolean var =userService.removeByIds(Arrays.asList(ids));
        return R.ok().put("Result",var);
    }
    @Login
    @GetMapping("/del")
    // @RequiresPermissions("exam:user:delete")
    public R del(String id){
        if(id ==null) return EMPTYdata();
//        Long[] ids = {Long.parseLong(id)};
//        delete(ids);
        System.out.println("get a id to delete lol"+id);
        System.out.println(Long.valueOf(id));
        boolean var = userService.delete(id);
        return BOOLresult(var);
    }
//    @ApiOperation("注册")
//    @PostMapping("/register")
//    public R register(@RequestBody UserEntity user) {
//        String re = adminService.register(user);
//        if(re.equals("err")){
//            return R.error(20001,"Invalid account!");
//        }
//        return R.ok().put("token",re);
//    }

    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public R info(String token) {
        if (token == null) R.ok().put("user", null);
        AdminDTO admin = adminService.getAdminInfo(token);
        return R.ok().put("admin", admin);
    }

    @ApiOperation("登出")
    @GetMapping("logout")
    public R logout(@RequestParam String token) {
        return R.error(50008," log out");
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("exam:admin:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("exam:admin:info")
    public R info(@PathVariable("id") Long id){
		AdminEntity admin = adminService.getById(id);

        return R.ok().put("admin", admin);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("exam:admin:save")
    public R save(@RequestBody AdminEntity admin){
		adminService.save(admin);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("exam:admin:update")
    public R update(@RequestBody AdminEntity admin){
		adminService.updateById(admin);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("exam:admin:delete")
    public R delete(@RequestBody Long[] ids){
		adminService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
