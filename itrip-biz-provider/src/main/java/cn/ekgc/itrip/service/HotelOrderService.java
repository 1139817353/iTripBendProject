package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.HotelOrder;

import java.util.List;

public interface HotelOrderService {
	/**
	 * <b>查询订单列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder> getListByQuery(HotelOrder query)throws Exception;
	/**
	 * <b>保存订单</b>
	 * @param hotelOrder
	 * @return
	 */
	boolean save(HotelOrder hotelOrder)throws Exception;

	/**
	 * <b>修改订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	boolean update(HotelOrder hotelOrder)throws Exception;
	/**
	 * <b>查询订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	HotelOrder getHotelOrderByNo(String orderNo)throws Exception;

	/**
	 * <b>根据订单Id查询</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
     HotelOrder getHotelOrderById(Long orderId)throws Exception;
}
