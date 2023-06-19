package edu.hubu.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author yruns
 * @email yruns.sh@qq.com
 * @date 2023-06-19 19:59:54
 */
@Data
@TableName("score")
public class ScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 测评id
	 */
	@TableId
	private Long examId;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 成绩
	 */
	private Double score;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 完成时间
	 */
	private Date finishTime;

}
