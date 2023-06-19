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
@TableName("question")
public class QuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 题干
	 */
	private String content;
	/**
	 * 选项A
	 */
	private String optionA;
	/**
	 * 选项B
	 */
	private String optionB;
	/**
	 * 选项C
	 */
	private String optionC;
	/**
	 * 选项D
	 */
	private String optionD;
	/**
	 * 选项E
	 */
	private String optionE;
	/**
	 * 选项F
	 */
	private String optionF;
	/**
	 * 答案
	 */
	private String answer;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 是否删除 1删除 null 0未删除
	 */
	private Integer isDelete;

}