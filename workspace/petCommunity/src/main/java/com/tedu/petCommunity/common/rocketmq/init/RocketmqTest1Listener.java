package com.tedu.petCommunity.common.rocketmq.init;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.tedu.petCommunity.common.util.ObjectMapperUtil;
import com.tedu.petCommunity.sys.dao.PetcChatDao;
import com.tedu.petCommunity.sys.entity.PetcChatPO;

/**
 * 监听消费
 * 
 * @author 阳昊 2020年3月3日 下午6:19:47
 */
@Service
public class RocketmqTest1Listener implements MessageListener {

	private Logger logger = LoggerFactory.getLogger(RocketmqTest1Listener.class);

	/**
	 * 由于消费者是多线程的，所以对象要用static+set注入，把对象的级别提升到进程， 这样多个线程就可以共用，但是无法调用父类的方法和变量
	 */
	protected static PetcChatDao chatDao;

	@Resource
	public void setTestDao(PetcChatDao chatDao) {
		RocketmqTest1Listener.chatDao = chatDao;// 加入持久层dao，可根据需求自行修改
	}

	@Override
	public Action consume(Message message, ConsumeContext context) {
		try {
			Long startTime = System.currentTimeMillis();

			byte[] body = message.getBody();
			String msg = new String(body);// 获取到接收的消息，由于接收到的是byte数组，所以需要转换成字符串

			PetcChatPO po = new PetcChatPO();
			po = ObjectMapperUtil.toObject(msg, po.getClass());
			System.out.println(po);
			chatDao.insertChatMessage(po);
//			chatService.insertChatMessage(commId, chatMessage);

			Long endTime = System.currentTimeMillis();
			System.out.println("单次消费耗时：" + (endTime - startTime) / 1000);
		} catch (Exception e) {
			logger.error("MessageListener.consume error:" + e.getMessage(), e);
		}

		logger.info("MessageListener.Receive message");
		// 如果想测试消息重投的功能,可以将Action.CommitMessage 替换成Action.ReconsumeLater
		return Action.CommitMessage;
	}

}
