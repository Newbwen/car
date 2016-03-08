package pengyi.core.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import pengyi.application.user.IBaseUserAppService;
import pengyi.domain.model.permission.Permission;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by YJH on 2016/3/7.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IBaseUserAppService baseUserAppService;

    /**
     * 认证信息
     *
     * @param token
     * @return
     * @throws org.apache.shiro.authc.AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        BaseUser user = null;
        try {
            user = baseUserAppService.searchByPhone(token.getPrincipal().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == user) {
            throw new UnknownAccountException(); //未知用户
        } else {
            if (user.getStatus() == false) {
                throw new LockedAccountException(); //账户被禁用
            }
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getPhone(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 授权信息
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        BaseUser user = null;
        String phone = (String) principals.getPrimaryPrincipal();
        try {
            user = baseUserAppService.searchByPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(this.getAllRoles(user));
        authorizationInfo.setStringPermissions(this.getAllPermissions(user));
        return authorizationInfo;
    }

    private Set<String> getAllRoles(BaseUser user) {
        Set<String> roles = new HashSet<String>();
        Role role = user.getUserRole();
        roles.add(role.getRoleName());
        return roles;
    }

    private Set<String> getAllPermissions(BaseUser user) {
        Set<String> permissions = new HashSet<String>();
        Role role = user.getUserRole();
        if (null != role) {
            if (role.getStatus() == true) {
                List<Permission> permissionList = role.getPermissions();
                if (!permissionList.isEmpty()) {
                    for (Permission permission : permissionList) {
                        if (permission.getStatus() == true) {
                            permissions.add(permission.getPermissionName());
                        }
                    }
                }
            }
        }
        return permissions;
    }
}