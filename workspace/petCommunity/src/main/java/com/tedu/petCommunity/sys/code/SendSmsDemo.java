package com.tedu.petCommunity.sys.code;


import org.springframework.stereotype.Service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.tedu.petCommunity.sys.util.getSixNumUtil;
@Service
public class SendSmsDemo {
	 public String sendSms(String mobile){
	String sixNum = getSixNumUtil.getSixNum();
	DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", 
			"LTAI4FdpDtqBJ1SnxFBGhyHZ", 
			"EKlPLLr9CoX7dhjQU88uGGdsBiYT7h");
	IAcsClient client = new DefaultAcsClient(profile);
	CommonRequest request = new CommonRequest();
	request.setSysDomain("dysmsapi.aliyuncs.com");
    request.setSysVersion("2017-05-25");
    request.setSysAction("SendSms");
    request.putQueryParameter("RegionId", "cn-hangzhou");
	request.putQueryParameter("PhoneNumbers", mobile);
	request.putQueryParameter("SignName", "宠物社区");
	request.putQueryParameter("TemplateCode", "SMS_178451533");
	request.putQueryParameter("TemplateParam",sixNum);
	try {
        CommonResponse response = client.getCommonResponse(request);
    } catch (ServerException e) {
        e.printStackTrace();
    } catch (ClientException e) {
        e.printStackTrace();
    }
	return sixNum;
	 }
}