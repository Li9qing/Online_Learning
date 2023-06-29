package edu.hubu.user.controller;

import java.util.Arrays;
import java.util.HashMap;
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

import static edu.hubu.user.utils.Result.*;
import static edu.hubu.user.utils.WrapperUtils.PraseAccess;
import static edu.hubu.user.utils.WrapperUtils.PraseGenderOnly;


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


    @Login
    @RequestMapping("/list")
    // @RequiresPermissions("exam:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }
    @GetMapping("findStudent")
    public R findStudent(String username,String gender) {
        if(username == null && gender == null){return  EMPTYdata();}
        Map<String,Object> m1 = new HashMap<>();

        System.out.println(username + "__" + gender);
        if(username.length()>0)m1.put("username",username);
        if(gender.length()>0)m1.put("gender",PraseGenderOnly(gender));

        m1.put("teacher_access",0);
        PageUtils page = userService.queryPage(m1);
        return R.ok().put("page", page);
    }
    @GetMapping("findTeacher")
    public R findTeacher(String username,String gender) {
        if(username == null && gender == null){return  EMPTYdata();}
        Map<String,Object> m1 = new HashMap<>();
        System.out.println(username + "__" + gender);
        if(username.length()>0)m1.put("username",username);
        if(gender.length()>0)m1.put("gender",PraseGenderOnly(gender));
        m1.put("teacher_access",1);
        PageUtils page = userService.queryPage(m1);
        return R.ok().put("page", page);
    }

    @GetMapping("/pageStudent")
    // @RequiresPermissions("exam:user:list")
    public R PagerStudent(String currentPage,String totalPage){
        Map<String,Object> params = new HashMap<>();
        params.put("page",currentPage);
        params.put("limit",totalPage);
        params.put("teacher_access",0);
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }
    @GetMapping("/pageTeacher")
    // @RequiresPermissions("exam:user:list")
    public R PagerTeacher(String currentPage,String totalPage){
        Map<String,Object> params = new HashMap<>();
        params.put("page",currentPage);
        params.put("limit",totalPage);
        params.put("teacher_access",1);
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("exam:user:info")
    public R info(@PathVariable("id") Long id){
        UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("exam:user:save")
    public R save(@RequestBody UserEntity user){

        if(userService.register(user).equals("err")) {
            return DUPlicate();
        }

        return R.ok();
    }


    /**
     * 修改
     */
    @PostMapping("/update")
    // @RequiresPermissions("exam:user:update")
    public R update(@RequestBody UserEntity user){
        System.out.println("get update USER :"+user);
        if(user == null||user.getId()==null)return EMPTYdata();

        PraseAccess(user);
        System.out.println(user);
        boolean var =userService.updateById(user);
//        PageUtils page = userService.queryPage(new HashMap<String,Object>());
        return BOOLresult(var);
    }


    @RequestMapping("/ban")
    // @RequiresPermissions("exam:user:delete")
    public R delete(@RequestBody String id){
        if(id == null)return EMPTYdata();

        return BOOLresult(userService.removeById(id));
    }
    @RequestMapping("/BatchDel")
    public R BatchDel(@RequestBody Long [] ids){
        if(ids.length==0)return EMPTYdata();
        boolean var =userService.removeByIds(Arrays.asList(ids));
        return BOOLresult(var);
    }
    @Login
    @GetMapping("/del")
    // @RequiresPermissions("exam:user:delete")
    public R del(String id){
        if(id ==null) return EMPTYdata();
        System.out.println("get a id to delete lol"+id);
        System.out.println(Long.valueOf(id));


        boolean var = userService.delete(id);
        return BOOLresult(var);
    }

}
