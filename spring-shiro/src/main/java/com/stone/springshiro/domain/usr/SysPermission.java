package com.stone.springshiro.domain.usr;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author honorstone
 * @date 2019/3/11 15:37
 */
@Entity
@Data
public class SysPermission implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;
    private String url;
    private String permission;
    private Long parentId;
    private String parentIds;
    private Boolean available = Boolean.FALSE;

    @ManyToMany
    @JoinTable(
            name = "SysRolePermission",
            joinColumns = {@JoinColumn(name = "permissionId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;
}
