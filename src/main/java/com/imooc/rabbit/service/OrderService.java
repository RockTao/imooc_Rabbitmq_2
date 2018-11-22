package com.imooc.rabbit.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.rabbit.constant.Constants;
import com.imooc.rabbit.entity.BrokerMessageLog;
import com.imooc.rabbit.entity.Order;
import com.imooc.rabbit.mapper.BrokerMessageLogMapper;
import com.imooc.rabbit.mapper.OrderMapper;
import com.imooc.rabbit.producer.RabbitOrderSender;
import com.imooc.rabbit.utils.DateUtils;
import com.imooc.rabbit.utils.FastJsonConvertUtil;

@Service
public class OrderService {

	
		@Autowired
	    private OrderMapper orderMapper;
	    @Autowired
	    private BrokerMessageLogMapper brokerMessageLogMapper;
	    @Autowired
	    private RabbitOrderSender orderSender;
	    

	    /**
	     * 创建订单
	     *
	     * @param order 订单
	     * @throws Exception 
	     */
	    public void createOrder(Order order) throws Exception {
	        // 当前时间
	        Date orderTime = new Date();
	        // 业务数据入库
//	        orderMapper.insertOrder(order);
	        // 消息日志入库
	        BrokerMessageLog messageLog = new BrokerMessageLog();
	        // 插入消息记录表数据
	        
	        messageLog.setMessageId(order.getMessageId());
	        
	        messageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(order));
	        messageLog.setStatus("0");
	        messageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
	        messageLog.setCreateTime(new Date());
	        messageLog.setUpdateTime(new Date());
	        this.brokerMessageLogMapper.insertSelective(messageLog);
	        // 发送消息
	        orderSender.sendOrder(order);
	    }
	    
	    public int testIntefact( Order order) {
	    	return      orderMapper.insertOrder(order);
	    }
}
