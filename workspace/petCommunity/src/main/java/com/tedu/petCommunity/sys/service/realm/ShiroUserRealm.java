package com.tedu.petCommunity.sys.service.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.petCommunity.sys.dao.SysUserDao;
import com.tedu.petCommunity.sys.entity.SysUser;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public CredentialsMatcher getCredentialsMatcher() {
		// 构建凭证匹配对象
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		// 设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		// 设置加密次数
		cMatcher.setHashIterations(1);
		return cMatcher;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		System.out.println("==doGetAuthorizationInfo==");
//		// 获取当前登陆的用户名
//		String username = (String) principals.fromRealm(getName()).iterator().next();
//		// 根据用户名查找对象
//		SysUser user = SysUserService.findByUserName(username);
//		if (permissions==null||permissions.size()==0) {
//			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//			// 添加角色(Set集合<字符串>)
//			Set<String> stringPermissions=new HashSet<>();
//			// 迭代用户对应的角色集合，为了获取角色对应的权限
//			for (String per:permissions) {
//				// 添加permission
//				info.addStringPermissions(g.getPermissionStringList());
//			}
//			return info;
//		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1.获取用户提交的信息
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		String username = uToken.getUsername();
		// 2.基于用户提交信息查询当前用户
		SysUser user = sysUserDao.findUserByUserName(username);
		// 3.检测用户是否存在
		if (user == null)
			throw new UnknownAccountException();
		// 4.封装用户信息，交给SecurityManager进行认证
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		return new SimpleAuthenticationInfo(user, // principal身份
				user.getPassword(), // hashedCredentials
				credentialsSalt, getName());
	}
}
