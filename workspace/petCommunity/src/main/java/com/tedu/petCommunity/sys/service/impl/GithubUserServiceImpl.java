package com.tedu.petCommunity.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tedu.petCommunity.sys.dao.PetcUserDao;
import com.tedu.petCommunity.sys.dto.AccessTokenDTO;
import com.tedu.petCommunity.sys.dto.GithubUser;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.provider.GithubProvider;
import com.tedu.petCommunity.sys.service.GithubUserService;

/**
 * @author 阳昊 2019年11月27日 下午3:44:46
 */
@Service
public class GithubUserServiceImpl implements GithubUserService {

	@Value("${github.client.id}")
	private String clientId;
	@Value("${github.client.secret}")
	private String clientSecret;
	@Value("${github.redirect.uri}")
	private String redirectUri;

	@Autowired
	private GithubProvider githubProvider;
	@Autowired
	private PetcUserDao userDao;

	@Override
	public void callback(String code, String state, HttpServletRequest request, HttpServletResponse response) {
		// 1.组织AccessTokenDTO对象
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri(redirectUri);
		accessTokenDTO.setState(state);
		// 2.根据AccessTokenDTO对象获取accessToken
		String accessToken = githubProvider.getAccessToken(accessTokenDTO);
		// 3.根据accessToken获取githubUser对象
		GithubUser githubUser = githubProvider.getUser(accessToken);
		if (githubUser == null) {
			return;
		}
		// 4.在数据库中查找是否已存在github账号
		String username = String.valueOf(githubUser.getId());
		List<PetcUserPO> users = userDao.getUserByUsername(username);
		// 5.如果不存在，则新插入一条github账号
		if (users == null || users.size() <= 0) {
			PetcUserPO po = new PetcUserPO();
			po.setUsername(username);
			String password = "password";
			String salt = UUID.randomUUID().toString();
			SimpleHash sh = new SimpleHash("MD5", password, salt, 1);
			String hex = sh.toHex();
			po.setSalt(salt);
			po.setPassword(hex);
			po.setNickname(githubUser.getName());
			po.setCreateTime(new Date());
			po.setModifiedTime(po.getCreateTime());
			userDao.insertAll(po);
		}

		// 6.登录成功，将用户信息交给shiro管理
		UsernamePasswordToken token = new UsernamePasswordToken(username, "password");
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
	}

	@Override
	public String getGithubUserAuthorizeUri() {
		StringBuilder sb = new StringBuilder();
		sb.append("https://github.com/login/oauth/authorize?client_id=");
		sb.append(clientId);
		sb.append("&redirect_uri=");
		sb.append(redirectUri);
		sb.append("&scope=user&state=1");
		return sb.toString();
	}

}
