package com.tent.master.shiro.token;

import com.tent.common.utils.Lg;
import com.tent.master.shiro.ShiroUsernamePasswordToken;
import com.tent.service.impl.hy.OperatorService;
import com.tent.service.impl.hy.PermissionService;
import com.tent.service.impl.hy.RoleService;
import com.tent.service.impl.hy.UserService;
import com.tent.service.impl.shiro.OperatorUser;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


public class SampleRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;
	
	public SampleRealm() {
		super();
	}
	/**
	 *  认证信息，主要针对用户登录， 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		ShiroUsernamePasswordToken token = (ShiroUsernamePasswordToken)authcToken;
		token.CheckCaptcha();

		Lg.info(SampleRealm.class,"验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		OperatorUser operatorUser = operatorService.findOperatorByUserName(token.getUsername());
		if(null == operatorUser){
			throw new AccountException("帐号或密码不正确！");
		/**
		 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
		 */
		}else{
			this.setSession("currentUser",token.getUsername());
			//更新登录时间 last login time
			operatorService.updateByPrimaryKeySelective(operatorUser);
		}
		return new SimpleAuthenticationInfo(operatorUser,operatorUser.getPassword(), getName());
    }

	 /** 
     * 授权 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		String userId = TokenManager.getUserId(); //静态类初始化失败，调用不成功(严重)

		OperatorUser token = (OperatorUser) SecurityUtils.getSubject().getPrincipal();

		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//根据用户ID查询角色（role），放入到Authorization里。
		Set<String> roles = roleService.findRoleByOperatorId(token);
		info.setRoles(roles);
		//根据用户ID查询权限（permission），放入到Authorization里。
		Set<String> permissions = permissionService.findPermissionByOperatorId(token);
		info.setStringPermissions(permissions);
        return info;  
    }  
    /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value){
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser){
			Session session = currentUser.getSession();
			Lg.info(SampleRealm.class,"Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if(null != session){
				session.setAttribute(key, value);
			}
		}
	}
}
