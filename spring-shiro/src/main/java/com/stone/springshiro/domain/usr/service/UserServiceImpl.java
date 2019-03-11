package com.stone.springshiro.domain.usr.service;

import com.stone.springshiro.domain.usr.UserInfo;
import com.stone.springshiro.repository.entity.dao.UserInfoDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author honorstone
 * @date 2019/3/11 16:14
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoDAO usrDAO;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("Inside UserServiceImpl::findByUsername");
        return usrDAO.findByUsername(username);
    }
}
