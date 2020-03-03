package com.tedu.petCommunity.rocketMQ;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;

public class ConsumerTest {
	public static void main(String[] args) {
		Properties properties = new Properties();
		// 您在控制台创建的 Group ID
		properties.put(PropertyKeyConst.GROUP_ID, "GID_rjshaonian");
		// 鉴权用 AccessKeyId，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.AccessKey, "LTAI4FnPvY6h5w92zg6ADs61");
		// 鉴权用 AccessKeySecret，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.SecretKey, "z1bI8UyuRut0Fdkq1WLNodXEE0N0Om");
		// 设置 TCP 接入域名，进入控制台的实例详情页面，在页面上方选择实例后，在实例信息中的“获取接入点信息”区域查看
		properties.put(PropertyKeyConst.NAMESRV_ADDR,
				"http://MQ_INST_1785275891834049_BcKAq53o.mq-internet-access.mq-internet.aliyuncs.com:80");

		Consumer consumer = ONSFactory.createConsumer(properties);
		consumer.subscribe("Topic_rjshaonian", "*", new MessageListener() {
			public Action consume(Message message, ConsumeContext context) {
				System.out.println("Receive: " + message);
				System.out.println("Context: " + context);
				return Action.CommitMessage;
			}
		});
		consumer.start();
		System.out.println("Consumer Started");
	}
}