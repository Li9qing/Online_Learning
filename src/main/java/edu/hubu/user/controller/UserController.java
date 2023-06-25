package edu.hubu.user.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import edu.hubu.common.utils.R;
import edu.hubu.course.entity.CourseNoteEntity;
import edu.hubu.course.entity.CourseStudyEntity;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.user.entity.Result;

import edu.hubu.user.entity.UserDTO;
import edu.hubu.user.entity.UserNoteEntity;
import edu.hubu.user.interceptor.Login;
import edu.hubu.user.interceptor.Register;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.hubu.user.entity.UserEntity;
import edu.hubu.user.service.UserService;
import edu.hubu.common.utils.PageUtils;

import static edu.hubu.user.utils.ResultCode.ERROR;


/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@RestController
@CrossOrigin
@RequestMapping("user/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @ApiOperation("登陆")
    @PostMapping("/login")
    public R login(@RequestBody UserEntity admin) {
        System.out.println("get Post");
        System.out.println(admin.toString());
        String token = userService.login(admin);
        if(token.equals("err")){
            return R.error(ERROR,"no such user!");
        }
        return R.ok().put("token", token);
    }
    @ApiOperation("注册")
    @PostMapping("/register")
    public R register(@RequestBody UserEntity user) {
        System.out.println(user);
        String re = userService.register(user);
        if(re.equals("err")){
            return R.error(ERROR,"Invalid account!");
        }
        return R.ok().put("token",re);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public R info(String token) {
        if (token == null) ERRtoken();
        UserDTO user = userService.getUserInfo(token);
        return R.ok().put("user", user);
    }

    @GetMapping("find")
    public R find(String username,String gender) {
        if(username == null && gender == null){return  EMPTYdata();}
        Map<String,Object> m1 = new HashMap<>();
        System.out.println(username + "__" + gender);
        m1.put("username",username);
        m1.put("gender",gender);
        m1.put("type",1);
        PageUtils page = userService.queryPage(m1);
        return R.ok().put("page", page);
    }

    @GetMapping("detailPerson")
    public R detail(String token){
        if (token == null) return ERRtoken();
        UserEntity user = userService.getUserDetail(token);

        return R.ok().put("user", user);
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
		userService.save(user);

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
		boolean var =userService.updateById(user);
//        PageUtils page = userService.queryPage(new HashMap<String,Object>());
        return R.ok().put("resujlt", var);
    }

    @RequestMapping("/ban")
    // @RequiresPermissions("exam:user:delete")
    public R delete(@RequestBody String id){
        if(id == null)return EMPTYdata();
		userService.removeById(id);

        return R.ok();
    }

    @Login
    @GetMapping("/del")
    // @RequiresPermissions("exam:user:delete")
    public R del(String id){
        if(id ==null) return EMPTYdata();
//        Long[] ids = {Long.parseLong(id)};
//        delete(ids);
        System.out.println("get a id to delete lol"+id);
        boolean var = userService.delete(id);
        return BOOLresult(var);
    }

    private R BOOLresult(boolean var) {
        return R.ok().put("result", var);
    }

    public R ERRtoken(){
        return R.error(ERROR,"no token!");

    }
    public R EMPTYdata(){

        return R.error(404,"no data!").put("count",0);
    }
    @GetMapping("/note")
    public R getNote(String token){
        if(token == null){
            return ERRtoken();
        }
        List<CourseNoteEntity>re =userService.getNoteList(token);
        if(re ==null){
            return EMPTYdata();
        }
        R u1 = R.ok();
        u1.put("Result",re);
        u1.put("count",re.size());
        return u1;
    }
    @GetMapping("/pageNote")
    // @RequiresPermissions("exam:user:list")
    public R pageNote(String currentPage,String totalPage,String token){
        if(token == null){
            return ERRtoken();
        }
        Map<String, Object> params = PATCHparamByuserId(currentPage, totalPage, token);
        PageUtils page = userService.NotePage(params);
        return R.ok().put("page", page);
    }

    private Map<String, Object> PATCHparamByuserId(String currentPage, String totalPage, String token) {
        Map<String,Object> params = new HashMap<>();
        params.put("page", currentPage);
        params.put("limit", totalPage);
        UserDTO d1 = userService.getUserInfo(token);
        params.put("userId",d1.getId());
        return params;
    }
    @GetMapping("/delNote")
    public R delNote(String courseId, String lessonId, String userId){
        System.out.println(courseId+"_"+lessonId+""+userId);
        if(courseId==null||lessonId==null||userId==null) return EMPTYdata();

        return BOOLresult(userService.delNote(courseId,lessonId,userId));
    }
    @PostMapping("/updNote")
    public R updNote(@RequestBody UserNoteEntity note){
        System.out.println("get update note :"+note);
        if(note == null||note.getUserId()==null)return EMPTYdata();
        boolean var =userService.updateNote(note);
//        PageUtils page = userService.queryPage(new HashMap<String,Object>());
        return R.ok().put("resujlt", var);
    }
    @GetMapping("/Course")
    public R getCourse(String token){
        if(token == null){
            return ERRtoken();
        }
        List<CourseStudyEntity>re =userService.getStudyList(token);
        if(re ==null){
            return EMPTYdata();
        }
        R u1 = R.ok();
        u1.put("Result",re);
        u1.put("count",re.size());
        return u1;
    }
    @GetMapping("/pageCourse")
    // @RequiresPermissions("exam:user:list")
    public R pageCourse(String currentPage,String totalPage,String token){
        if(token == null){
            return ERRtoken();
        }
        Map<String, Object> params = PATCHparamByuserId(currentPage, totalPage, token);
        PageUtils page = userService.CoursePage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/Submit")
    public R getSubmit(String token){
        if(token == null){
            return ERRtoken();
        }
        List<SubmitEntity>re =userService.getSubmitList(token);
        if(re ==null){
            return EMPTYdata();
        }
        R u1 = R.ok();
        u1.put("Result",re);
        u1.put("count",re.size());
        return u1;
    }
    @GetMapping("/pageSubmit")
    // @RequiresPermissions("exam:user:list")
    public R pageSubmit(String currentPage,String totalPage,String token){
        if(token == null){
            return ERRtoken();
        }
        Map<String, Object> params = PATCHparamByuserId(currentPage, totalPage, token);
        PageUtils page = userService.SubmitPage(params);
        return R.ok().put("page", page);
    }

}
