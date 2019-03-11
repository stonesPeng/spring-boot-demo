package com.stone.springshiro.domain.usr;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;
import org.joda.time.DateTime;

/**
 * @author honorstone
 * @date 2019/3/11 17:20
 */
@Data
public class UserToken implements AuthenticationToken {

    private Integer userId;

    private String token;

    private DateTime createTime;

    private DateTime expiredTime;

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
