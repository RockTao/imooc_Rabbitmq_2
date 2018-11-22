package com.imooc.rabbit.mapper;

import com.imooc.rabbit.entity.Order;

public interface OrderMapper {

	int insertOrder(Order record);
	int deleteByPrimaryKey(Integer id);
	int insertSelective(Order record);
	Order selectByPrimaryKey(Integer id);
	int updateByPrimaryKeySelective(Order record);
	int updateByPrimaryKey(Order record);
}
