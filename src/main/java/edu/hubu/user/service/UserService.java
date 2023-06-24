package edu.hubu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hubu.common.utils.PageUtils;
import edu.hubu.course.entity.CourseCommentEntity;
import edu.hubu.course.entity.CourseNoteEntity;
import edu.hubu.course.entity.CourseStudentEntity;
import edu.hubu.course.entity.CourseStudyEntity;
import edu.hubu.exam.entity.ScoreEntity;
import edu.hubu.exam.entity.SubmitEntity;
import edu.hubu.group.entity.ForumCommentEntity;
import edu.hubu.group.entity.GroupMemberEntity;
import edu.hubu.message.entity.MessageAttrEntity;
import edu.hubu.user.entity.UserDTO;
import edu.hubu.user.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
    String login(UserEntity admin);
    String register(UserEntity user);

    UserDTO getUserInfo(String token);
    UserEntity getUserDetail(String token);
    void logout(String token);
    //course obj
    List<CourseNoteEntity> getNoteList(String token);
    List<CourseStudentEntity> getCourseList(String token);
    List<CourseCommentEntity> getCommentList(String token);
    List<CourseStudyEntity> getStudyList(String token);
    //course obj
    //quiz
    List<ScoreEntity> getScoreList(String token);
    List<SubmitEntity> getSubmitList(String token);
    //quiz
    //Group
    List<GroupMemberEntity> getGroupList(String token);
    List<ForumCommentEntity> getGroupCommentList(String token);
    //Message
    List<MessageAttrEntity> getSendList();
    List<MessageAttrEntity> getReciveList();

    List<UserEntity> findUserInfo(String username);

    boolean delete(String id);
}

