package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.HotelOrderDao;
import cn.ekgc.itrip.pojo.entity.HotelOrder;
import cn.ekgc.itrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("hotelOrderService")
@Transactional
public class HotelOrderServiceimpl implements HotelOrderService {
	@Autowired
	private HotelOrderDao hotelOrderDao;

	/**
	 * <b>查询订单列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<HotelOrder> getListByQuery(HotelOrder query) throws Exception {
		List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrderListByQuery(query);
		if (hotelOrderList != null) {
			return hotelOrderList;
		}
		return new ArrayList<HotelOrder>();
	}

	/**
	 * <b>保存订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	public boolean save(HotelOrder hotelOrder) throws Exception {
	    int count = hotelOrderDao.save(hotelOrder);
	    if (count > 0){
	    	return true;
	    }
		return false;
	}

	/**
	 * <b>修改订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	public boolean update(HotelOrder hotelOrder) throws Exception {
		int count = hotelOrderDao.update(hotelOrder);
		if (count > 0 ){
			return true;
		}
		return false;
	}

	/**
	 * <b>根据订单编号查询</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	public HotelOrder getHotelOrderByNo(String orderNo) throws Exception {
		HotelOrder query = new HotelOrder();
		query.setOrderNo(orderNo);

		List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrderListByQuery(query);
		if (hotelOrderList != null && hotelOrderList.size() > 0 ){
			return hotelOrderList.get(0);
		}
		return null;
	}

	/**
	 * <b>根据订单Id查询</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public HotelOrder getHotelOrderById(Long orderId) throws Exception {
       HotelOrder query = new HotelOrder();
       query.setId(orderId);

       List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrderListByQuery(query);
       if (hotelOrderList != null && hotelOrderList.size()>0){
       	return hotelOrderList.get(0);
       }
		return null;
	}
}
