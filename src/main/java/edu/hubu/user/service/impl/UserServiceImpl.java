package edu.hubu.user.service.impl;

import com.alibaba.fastjson.JSON;
import edu.hubu.course.entity.*;
import edu.hubu.course.service.CourseLessonService;
import edu.hubu.course.service.impl.*;
import edu.hubu.exam.entity.ScoreEntity;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.exam.service.impl.SubmitServiceImpl;
import edu.hubu.group.entity.ForumCommentEntity;
import edu.hubu.group.entity.GroupMemberEntity;
import edu.hubu.message.entity.MessageAttrEntity;
import edu.hubu.user.entity.UserDTO;
import edu.hubu.user.entity.UserNoteEntity;
import edu.hubu.user.exception.EduException;
import edu.hubu.user.utils.JwtUtils;
import edu.hubu.user.utils.MD5;
import edu.hubu.user.utils.WrapperUtils;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.common.utils.Query;

import edu.hubu.user.dao.UserDao;
import edu.hubu.user.entity.UserEntity;
import edu.hubu.user.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import static java.util.stream.Collectors.toList;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Resource
    UserDao DaoUser;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    CourseNoteServiceImpl Note;
    @Autowired
    SubmitServiceImpl Submit;


    @Autowired
    CourseStudyServiceImpl Course;
    @Autowired
    CourseServiceImpl CE;
    @Autowired
    CourseLessonServiceImpl CEL;


    @Override
    public String register(UserEntity user) {
        String nick = user.getNickName();
        String username = user.getUsername();
        String password = user.getPassword();
        String access = user.getTeacherAccess().equals("false") ? "0":"1";

        System.out.println(username + "____________" + password+"___________"+access);
        System.out.println(MD5.encrypt(password));
        QueryWrapper<UserEntity> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        UserEntity u1 = this.getOne(userQueryWrapper);
        if (u1 != null||username.length()==0) {
            return "err";

        }

        user.setCreateTime(new Date());
        user.setStatus(0);
        System.out.println(user);
        this.save(user);
        UserEntity userDetails = this.getOne(userQueryWrapper);
        String jwtToken = JwtUtils.getJwtToken(userDetails.getId().toString(), userDetails.getUsername(),userDetails.getTeacherAccess());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);
        // 加入到redis中
        System.out.println(jwtToken);
        stringRedisTemplate.opsForValue().set(jwtToken, JSON.toJSONString(userDTO), 1, TimeUnit.DAYS);
        return jwtToken;

    }


    @Override
    public String login(UserEntity user) {
        if (user == null) {
            System.out.println("POST struct err!!!!!!!");
        }
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(username + "____________" + password);
        QueryWrapper<UserEntity> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        userQueryWrapper.eq("password", password);

        UserEntity userDetails = this.getOne(userQueryWrapper);

        //if (userDetails != null && !MD5.encrypt(password).equals(userDetails.getPassword()))
        //throw new EduException("用户名或密码错误!");

        if (userDetails == null) {

            return "err";
        }

        System.out.println(userDetails + "is Result");
        String jwtToken = JwtUtils.getJwtToken(userDetails.getId().toString(), userDetails.getUsername(), userDetails.getTeacherAccess());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);
        // 加入到redis中
        System.out.println(jwtToken);
        stringRedisTemplate.opsForValue().set(jwtToken, JSON.toJSONString(userDTO), 1, TimeUnit.DAYS);
        return jwtToken;
    }


    @Override
    public UserDTO getUserInfo(String token) {
        String userJson = stringRedisTemplate.opsForValue().get(token);
        UserDTO e1 = JSON.parseObject(userJson, UserDTO.class);
        if(e1 == null){return  null;}
        QueryWrapper<UserEntity> x2 = new QueryWrapper<>();
        x2.eq("username",e1.getUsername());
        UserEntity e2 =this.getOne(x2);
        e1.setId(e2.getId().toString());
        System.out.println(e1);

        return e1;
    }

    public UserEntity findUserInfo(String username) {

        QueryWrapper<UserEntity> x2 = new QueryWrapper<>();
        x2.eq("username",username);
        UserEntity e2 =this.getOne(x2);

        return e2;
    }

    @Override
    public boolean delete(String id) {
        QueryWrapper<UserEntity> e1 = new QueryWrapper<>();
        int n = DaoUser.delDATAbyid(id);
        e1.eq("id",id);
//        Integer i1 = this.getBaseMapper().delete(e1);
        return n>0;
    }

    @Override
    public UserEntity getUserDetail(String token) {
        String userJson = stringRedisTemplate.opsForValue().get(token);
        UserDTO index = JSON.parseObject(userJson, UserDTO.class);
        QueryWrapper<UserEntity> Re = new QueryWrapper<>();
        Re.eq("username",index.getUsername());

        return this.getOne(Re);
    }


    @Override
    public void logout(String token) {
        // 从redis中将token移除
        stringRedisTemplate.delete(token);
    }

    @Override
    public List<CourseNoteEntity> getNoteList(String token) {
        UserDTO d1 = getUserInfo(token);
        QueryWrapper<CourseNoteEntity> quest =  WrapperUtils.getWrapperByUserId(
                CourseNoteEntity.class,d1 != null?d1.getId().toString():""
        );

        return Note.list(quest);

    }

    @Override
    public List<CourseStudentEntity> getCourseList(String token) {
        return null;
    }

    @Override
    public List<CourseCommentEntity> getCommentList(String token) {
        return null;
    }

    @Override
    public List<CourseStudyEntity> getStudyList(String token) {
        UserDTO d1 = getUserInfo(token);
        QueryWrapper<CourseStudyEntity> quest =  WrapperUtils.getWrapperByUserId(
                CourseStudyEntity.class, d1 != null?d1.getId().toString():""
        );
        return Course.list(quest);
    }

    @Override
    public List<ScoreEntity> getScoreList(String token) {
        return null;
    }

    @Override
    public List<SubmitEntity> getSubmitList(String token) {
        UserDTO d1 = getUserInfo(token);
        QueryWrapper<SubmitEntity> quest =  WrapperUtils.getWrapperByUserId(
                SubmitEntity.class,d1 != null?d1.getId().toString():""
        );

        return Submit.list(quest);
    }

    @Override
    public List<GroupMemberEntity> getGroupList(String token) {

        return null;
    }

    @Override
    public List<ForumCommentEntity> getGroupCommentList(String token) {
        return null;
    }

    @Override
    public List<MessageAttrEntity> getSendList() {
        return null;
    }

    @Override
    public List<MessageAttrEntity> getReciveList() {
        return null;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<UserEntity> e1 = new QueryWrapper<>();
        e1.eq("teacher_access",params.get("teacher_access"));
        if(params.size()>2){

            if(params.containsKey("username"))e1.eq("username",params.get("username"));
            if(params.containsKey("username"))e1.eq("username",params.get("username"));
        }
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                e1
        );
        page.setTotal(page.getRecords().size());
        page.setPages(page.getTotal()/page.getSize()+1);
        System.out.println(page.getTotal());
        System.out.println(page.getSize());
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        return new PageUtils(page);
    }

    @Override
    public boolean delNote(String courseId, String lessonId, String userId) {
        QueryWrapper<CourseNoteEntity> q1 = new QueryWrapper<>();
        q1.eq("user_id",userId);
        q1.eq("course_id",courseId);
        q1.eq("lesson_id",lessonId);
        Note.remove(q1);
        return false;
    }

    @Override
    public PageUtils NotePage(Map<String, Object> params) {
        QueryWrapper<CourseNoteEntity> e1 = new QueryWrapper<>();
        e1.eq("user_id",params.get("userId"));
        IPage<CourseNoteEntity> page = Note.page(
                new Query<CourseNoteEntity>().getPage(params),
                e1
        );
        List<CourseNoteEntity> RAW = page.getRecords();
        if(RAW.size()==0){
            new PageUtils(null,0,0,0);
        }
        List<Long> Courseid = RAW.stream().map(CourseNoteEntity::getCourseId).collect(toList());
        List<Long> Lessonid = RAW.stream().map(CourseNoteEntity::getLessonId).collect(toList());
        System.out.println(Courseid);

        List<CourseEntity> e2 = CE.listByIds(Courseid);
        QueryWrapper<CourseLessonEntity> LESSON = new QueryWrapper<>();
        LESSON.in("id", Lessonid);
        LESSON.in("course_id", Courseid);
        List<CourseLessonEntity> e3 = CEL.list(LESSON);

        List<UserNoteEntity> RESULT = new ArrayList<>();
        for(int i =0;i<RAW.size();i++){

            RESULT.add(i, new UserNoteEntity(RAW.get(i),e2.get(i),e3.get(i)));
        }

        page.setTotal(page.getRecords().size());
        page.setPages(page.getTotal()/page.getSize()+1);

        System.out.println(page.getTotal());
        System.out.println(page.getSize());
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());

        return new PageUtils(RESULT,(int)page.getTotal(),(int)page.getSize(),(int)page.getCurrent());
    }

    @Override
    public PageUtils CoursePage(Map<String, Object> params) {
        QueryWrapper<CourseStudyEntity> e1 = new QueryWrapper<>();
        e1.eq("user_id",params.get("userId"));
        IPage<CourseStudyEntity> page = Course.page(
                new Query<CourseStudyEntity>().getPage(params),
                e1
        );
        page.setTotal(page.getRecords().size());
        page.setPages(page.getTotal()/page.getSize()+1);
        System.out.println(page.getTotal());
        System.out.println(page.getSize());
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        return new PageUtils(page);
    }

    @Override
    public PageUtils SubmitPage(Map<String, Object> params) {
        QueryWrapper<SubmitEntity> e1 = new QueryWrapper<>();
        e1.eq("user_id",params.get("userId"));
        IPage<SubmitEntity> page = Submit.page(
                new Query<SubmitEntity>().getPage(params),
                e1
        );
        page.setTotal(page.getRecords().size());
        page.setPages(page.getTotal()/page.getSize()+1);
        System.out.println(page.getTotal());
        System.out.println(page.getSize());
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        return new PageUtils(page);
    }
}