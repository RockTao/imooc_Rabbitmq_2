package com.imooc.rabbit.producer;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imooc.rabbit.constant.Constants;
import com.imooc.rabbit.entity.BrokerMessageLog;
import com.imooc.rabbit.entity.Order;
import com.imooc.rabbit.mapper.BrokerMessageLogMapper;

@Component
public class RabbitOrderSender {


	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private BrokerMessageLogMapper brokerMessageLogMapper;

	public void sendOrder(Order order) throws Exception {
		rabbitTemplate.setConfirmCallback(confirmCallback);
		CorrelationData correlationData=new CorrelationData();
		correlationData.setId(order.getMessageId());
		rabbitTemplate.convertAndSend("order-exchange",//exchange
				"order.ABC",//路由建
				order, //消息体内容
				correlationData);//correlationData  消息的唯一ID
	}


	/**
	 * 回调方法：confirm确认
	 */
	private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String cause) {
			System.out.println("correlationData：" + correlationData);
			String messageId = correlationData.getId();
			if (ack) {
				// 如果confirm返回成功，则进行更新
//				BrokerMessageLog messageLogPO = new BrokerMessageLog();
//				messageLogPO.setMessageId(messageId);
//				messageLogPO.setStatus(Constants.ORDER_SEND_SUCCESS);
				brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, 
						new Date());
			} else {
				// 失败则进行具体的后续操作：重试或者补偿等
				System.out.println("异常处理...");
			}
		}
	};

}
