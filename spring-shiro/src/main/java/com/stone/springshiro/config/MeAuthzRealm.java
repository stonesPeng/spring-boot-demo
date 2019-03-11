package com.stone.springshiro.config;

import com.stone.springshiro.domain.usr.SysPermission;
import com.stone.springshiro.domain.usr.SysRole;
import com.stone.springshiro.domain.usr.UserInfo;
import com.stone.springshiro.domain.usr.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @author honorstone
 * @date 2019/3/11 16:07
 */
public class MeAuthzRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 获取授权(角色，权限)信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("Inside MeAuthzRealm::doGetAuthorizationInfo");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        for (SysRole sysRole : userInfo.getRoleList()) {
             authorizationInfo.addRole(sysRole.getRole());
            for (SysPermission permission : sysRole.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 获取身份验证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("Inside MeAuthzRealm::doGetAuthenticationInfo");
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        UserInfo info = userService.findByUsername(username);
        if(null == info){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                info,
                info.getPassword(),
                ByteSource.Util.bytes(info.getCredentialsSalt()),
                getName()

        );
        return authenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return super.supports(token);
        //return token instanceof UserToken;
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        super.onLogout(principals);
    }
}
