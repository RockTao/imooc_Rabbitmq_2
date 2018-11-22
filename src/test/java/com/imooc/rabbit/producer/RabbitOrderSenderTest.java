package com.imooc.rabbit.producer;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.rabbit.entity.Order;
import com.imooc.rabbit.mapper.OrderMapper;
import com.imooc.rabbit.service.OrderService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitOrderSenderTest {


	//	@Autowired 
	//	private RabbitOrderSender orderSender;
	@Autowired
	private OrderService orderSender;
	@Test
	public void testCreateOrder() throws Exception {
		Order  order =new Order();
		order.setId("20180818000000002");
		order.setName("测试订单1");
		order.setMessageId(System.currentTimeMillis()+"$" +UUID.randomUUID());
		orderSender.createOrder(order);
	}


	//	@Test
	public void send() throws Exception {
		Order  order =new Order();
		order.setId("20180818000000002");
		order.setName("测试订单1");
		order.setMessageId(System.currentTimeMillis()+"$" +UUID.randomUUID());
		orderSender.createOrder(order);
	}

	//	private Static SimpleDataFormat simpleDateFormat = new 

	//	@Test
	//	public void testSender1()throws Exception{
	//		Map<String,Object> properties = new HashMap<>();
	//		properties.put("number","12345");
	//		properties.put("send_time", Simple)
	//	}
}
