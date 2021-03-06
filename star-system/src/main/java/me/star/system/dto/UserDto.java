package me.star.system.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import me.star.base.BaseDTO;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/8 19:47
 */
@Data
public class UserDto extends BaseDTO implements Serializable {

    private Long id;

    private Set<RoleSmallDto> roles;

    private Set<JobSmallDto> jobs;

    private DeptSmallDto dept;

    private Long deptId;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String gender;

    private String avatarName;

    private String avatarPath;

    @JSONField(serialize = false)
    private String password;

    private Boolean enabled;

    @JSONField(serialize = false)
    private Boolean isAdmin = false;

    private Date pwdResetTime;
}
