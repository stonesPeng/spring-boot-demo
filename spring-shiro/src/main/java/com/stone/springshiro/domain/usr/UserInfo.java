package com.stone.springshiro.domain.usr;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author honorstone
 * @date 2019/3/11 15:32
 */
@Entity
@Data
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    private Integer uid;
    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    private String salt;
    private byte state;

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
            name = "SysUserRole",
            joinColumns = { @JoinColumn(name = "uid") },
            inverseJoinColumns ={@JoinColumn(name = "roleId") })

    private List<SysRole> roleList;

    public String getCredentialsSalt(){
        return username+salt;
    }
}
