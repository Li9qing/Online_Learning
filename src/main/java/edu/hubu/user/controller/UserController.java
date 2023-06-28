package edu.hubu.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import edu.hubu.common.utils.R;
import edu.hubu.course.entity.CourseNoteEntity;
import edu.hubu.course.entity.CourseStudyEntity;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.user.entity.*;

import edu.hubu.user.interceptor.Login;
import edu.hubu.user.interceptor.Register;
import edu.hubu.user.service.impl.UserFavorServiceImpl;
import edu.hubu.user.service.impl.UserFolderServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.hubu.user.service.UserService;
import edu.hubu.common.utils.PageUtils;
import org.springframework.web.multipart.MultipartFile;

import static edu.hubu.user.utils.Result.*;
import static edu.hubu.user.utils.ResultCode.ERROR;
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
@CrossOrigin
@RequestMapping("user/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserFavorServiceImpl UFR;
    @Autowired
    private UserFolderServiceImpl UFD;
    private final String DIR = "C:\\Users\\K\\Videos\\summer_camp\\SELF\\images";
    /**
     * 列表
     */
    @ApiOperation("登陆")
    @PostMapping("/login")
    public R login(@RequestBody UserEntity user) {
        System.out.println(user.toString());
        String token = userService.login(user);
        if(token.equals("err")){
            return R.error(ERROR,"no such user or consider BAN");
        }
        return R.ok().put("token", token);
    }
    @ApiOperation("注册")
    @PostMapping("/register")
    public R register(@RequestBody UserEntity user) {
        if(user == null) return EMPTYdata();
        System.out.println(user);
        PraseAccess(user);
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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////个人中心//////////////////////////////////////////////////////////////
//////////////////////////////信息设置//////////////////////////////////////////////////
    @ApiOperation("保存修改个人信息")
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

//////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////收藏管理//////////////////////////////////////////

    @GetMapping("/foldFavor")
    public R FoldFavor(String userId){

        if(userService.getBaseMapper().selectById(userId)==null) return EMPTYdata();
        List<UserFolder>fold =UFD.getFolders(userId);
        List<UserFavor>favor =  UFR.getFavors(userId);
        R n1 =  R.ok();
        n1.put("fold",fold);
        n1.put("favor",favor);
        return n1;

    }









/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////笔记管理///////////////////////////////////////////////////////////////////////////////////////////
    @ApiOperation("首页信息展示")
    @GetMapping("/note")
    public R getNote(String token){
        if(token == null){
            return ERRtoken();
        }
        List<CourseNoteEntity>re =userService.getNoteList(token);
        if(re ==null){
            return EMPTYdata();
        }
        return PatchListResult(re);
    }
    @GetMapping("/pageNote")
    // @RequiresPermissions("exam:user:list")
    public R pageNote(String currentPage,String totalPage,String token){
        if(token == null){
            return ERRtoken();
        }
        UserDTO d1 = userService.getUserInfo(token);
        if(d1==null)return ERRtoken();
        Map<String, Object> params = PATCHparamByuserId(currentPage, totalPage, d1.getId());
        PageUtils page = userService.NotePage(params);
        return R.ok().put("page", page);
    }

    private Map<String, Object> PATCHparamByuserId(String currentPage, String totalPage, String userId) {
        Map<String,Object> params = new HashMap<>();
        params.put("page", currentPage);
        params.put("limit", totalPage);

        params.put("userId",userId);
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
//    @GetMapping("/findNote")
//    public R findNote(String coursename,String chapter,String token) {
//        if(coursename == null || chapter == null||token==null){return  EMPTYdata();}
//
//        Map<String,Object> m1 = new HashMap<>();
//        System.out.println(coursename + "__" + chapter + "__"+);
//
//
//        return R.ok().put("page", page);
//    }
//////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////课程管理历史管理/////////////////////////////////////////////////////////
    @ApiOperation("首页课程展示")
    @GetMapping("/Course")
    public R getCourse(String token){
        if(token == null){
            return ERRtoken();
        }
        List<CourseStudyEntity>re =userService.getStudyList(token);
        if(re ==null){
            return EMPTYdata();
        }
        return PatchListResult(re);
    }
    @GetMapping("/delCourse")
    public R delCourse(String courseId, String lessonId, String userId){
        System.out.println(courseId+"_"+lessonId+""+userId);
        if(courseId==null||lessonId==null||userId==null) return EMPTYdata();

        return BOOLresult(userService.delCourse(courseId,lessonId,userId));
    }
    @GetMapping("/pageCourse")
    // @RequiresPermissions("exam:user:list")
    public R pageCourse(String currentPage,String totalPage,String token){
        if(token == null){
            return ERRtoken();
        }
        UserDTO d1 = userService.getUserInfo(token);
        if(d1==null)return ERRtoken();
        Map<String, Object> params = PATCHparamByuserId(currentPage, totalPage, d1.getId());
        PageUtils page = userService.CoursePage(params);
        return R.ok().put("page", page);
    }
    @GetMapping("/pageCoursIng")
    // @RequiresPermissions("exam:user:list")
    public R pageCoursIng(String currentPage,String totalPage,String token){
        if(token == null){
            return ERRtoken();
        }
        UserDTO d1 = userService.getUserInfo(token);
        if(d1==null)return ERRtoken();
        Map<String, Object> params = PATCHparamByuserId(currentPage, totalPage, d1.getId());
        params.put("finish",0);
        params.put("finish_time",new Date());
        PageUtils page = userService.CoursePage(params);
        return R.ok().put("page", page);
    }
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
    @ApiOperation("首页测评展示")
    @GetMapping("/Submit")
    public R getSubmit(String token){
        if(token == null){
            return ERRtoken();
        }
        List<SubmitEntity>re =userService.getSubmitList(token);
        if(re ==null){
            return EMPTYdata();
        }
        return PatchListResult(re);
    }
    @GetMapping("/pageSubmit")
    // @RequiresPermissions("exam:user:list")
    public R pageSubmit(String currentPage,String totalPage,String token){
        if(token == null){
            return ERRtoken();
        }
        UserDTO d1 = userService.getUserInfo(token);
        if(d1==null)return ERRtoken();
        Map<String, Object> params = PATCHparamByuserId(currentPage, totalPage, d1.getId());
        PageUtils page = userService.SubmitPage(params);
        return R.ok().put("page", page);
    }

}
