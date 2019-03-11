package com.stone.springshiro.domain.usr.service;

import com.stone.springshiro.domain.usr.UserInfo;

/**
 * @Author honorstone
 * @Date 2019/3/11 16:12
 */
public interface UserService {
    UserInfo findByUsername(String username);
}
