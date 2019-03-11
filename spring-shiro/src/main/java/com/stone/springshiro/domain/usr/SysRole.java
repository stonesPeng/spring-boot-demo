package com.stone.springshiro.domain.usr;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author honorstone
 * @date 2019/3/11 15:35
 */
@Entity
@Data
public class SysRole implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String role;
    private String description;
    private Boolean available = Boolean.FALSE;

    @ManyToMany
    @JoinTable(
            name="SysUserRole",
            joinColumns={@JoinColumn(name="roleId")},
            inverseJoinColumns={@JoinColumn(name="uid")})
    private List<UserInfo> userInfos;

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
            name="SysRolePermission",
            joinColumns={@JoinColumn(name="roleId")},
            inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<SysPermission> permissions;
}
