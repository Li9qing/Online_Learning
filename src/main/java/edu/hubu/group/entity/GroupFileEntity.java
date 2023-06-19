package edu.hubu.group.entity;

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
 * @date 2023-06-19 20:32:03
 */
@Data
@TableName("group_file")
public class GroupFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long fileId;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String url;
	/**
	 * 上传时间
	 */
	private Date updateTime;
	/**
	 * 所属群组id
	 */
	private Long groupId;

}
