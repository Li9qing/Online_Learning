package edu.hubu.user.entity;

import edu.hubu.course.entity.CourseEntity;
import edu.hubu.course.entity.CourseLessonEntity;
import edu.hubu.course.entity.CourseNoteEntity;
import edu.hubu.course.entity.CourseStudyEntity;
import lombok.Data;

import java.util.Date;

import static sun.security.krb5.Confounder.intValue;

@Data
public class UserCourseEntity {
    public static  UserCourseEntity make(CourseStudyEntity e1, CourseEntity e2, CourseLessonEntity e3){
        UserCourseEntity n1 = new UserCourseEntity();
        n1.setCourseId(e1.getCourseId());
        n1.setCoursename(e2.getName());
        n1.setUserId(e1.getUserId());
        n1.setLessonId(e1.getLessonId());
        n1.setChapter(e3.getChapter());
        n1.setProgress(e1.getProgress());
        n1.setFinish(e1.getFinish());
        n1.setCreateTime(e1.getCreateTime());
        n1.setUpdateTime(e1.getUpdateTime());
        return n1;
    }
    private Integer courseId;
    private String coursename;
    /**
     *
     */
    private Long userId;
    /**
     *
     */
    private String chapter;
    private Long lessonId;
    /**
     * 学习进度
     */
    private Integer progress;
    /**
     * 是否完成
     */
    private Integer finish;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;
}
