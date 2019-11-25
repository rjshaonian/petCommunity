package com.tedu.petCommunity.sys.util;

import java.util.Random;


public class getSixNumUtil {
	/**创建六位随机数字*/
	public static String getSixNum() {
		Random random = new Random();
		String result="";
		for (int i=0;i<6;i++)
		{
			result+=random.nextInt(10);
		}
		return "{\"code\":\""+result+"\"}";
	}
	
}

