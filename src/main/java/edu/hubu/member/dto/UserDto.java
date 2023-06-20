package edu.hubu.member.dto;

import lombok.Data;

/**
 * UserDto for
 *
 * @Author yruns
 * @Version 2023/6/20
 */
@Data
public class UserDto {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 是否有教师权限 1有 0无
     */
    private Integer authority;
    /**
     * 昵称
     */
    private String nickName;

    public UserDto(Long id, Integer authority, String nickName) {
        this.id = id;
        this.authority = authority;
        this.nickName = nickName;
    }

    public UserDto() {}
}
