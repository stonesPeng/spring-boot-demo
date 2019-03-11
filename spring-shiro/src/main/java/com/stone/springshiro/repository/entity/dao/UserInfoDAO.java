package com.stone.springshiro.repository.entity.dao;

import com.stone.springshiro.domain.usr.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author honorstone
 * @date 2019/3/11 16:15
 */
public interface UserInfoDAO extends CrudRepository<UserInfo,Long> {
    UserInfo findByUsername(String username);
}
