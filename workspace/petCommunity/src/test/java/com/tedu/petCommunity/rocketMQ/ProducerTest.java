package com.tedu.petCommunity.rocketMQ;

import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;

public class ProducerTest {
	@Test
	public void producerTest() {
		Properties properties = new Properties();
		// 您在控制台创建的 Group ID
		properties.put(PropertyKeyConst.GROUP_ID, "GID_rjshaonian");
		// 鉴权用 AccessKeyId，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.AccessKey, "LTAI4FnPvY6h5w92zg6ADs61");
		// 鉴权用 AccessKeySecret，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.SecretKey, "z1bI8UyuRut0Fdkq1WLNodXEE0N0Om ");
		// 设置 TCP 接入域名，进入控制台的实例详情页面，在页面上方选择实例后，在实例信息中的“获取接入点信息”区域查看
		properties.put(PropertyKeyConst.NAMESRV_ADDR,
				"http://MQ_INST_1785275891834049_BcKAq53o.mq-internet-access.mq-internet.aliyuncs.com:80");

		Producer producer = ONSFactory.createProducer(properties);
		// 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可
		producer.start();

		// 循环发送消息
//		while (true) {
		Message msg = new Message( //
				// 在控制台创建的 Topic，即该消息所属的 Topic 名称
				"Topic_rjshaonian",
				// Message Tag,
				// 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在消息队列 RocketMQ 版服务器过滤
				"TagA",
				// Message Body
				// 任何二进制形式的数据，消息队列 RocketMQ 版不做任何干预，
				// 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
				"Hello MQ".getBytes());
		// 设置代表消息的业务关键属性，请尽可能全局唯一，以方便您在无法正常收到消息情况下，可通过控制台查询消息并补发
		// 注意：不设置也不会影响消息正常收发
		msg.setKey("ORDERID_100");
		// 发送消息，只要不抛异常就是成功
		// 打印 Message ID，以便用于消息发送状态查询
		SendResult sendResult = producer.send(msg);
		System.out.println("Send Message success. Message ID is: " + sendResult.getMessageId());
//		}

		// 在应用退出前，可以销毁 Producer 对象
		// 注意：如果不销毁也没有问题
		// producer.shutdown();
	}
}