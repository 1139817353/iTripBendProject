package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.HotelOrder;
import cn.ekgc.itrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("hotelOrderTransport")
@RequestMapping("/hotelorder/trans")
public class HotelOrderTransportimpl implements HotelOrderTransport {
     @Autowired
     private HotelOrderService hotelOrderService;

	/**
	 * <b>查询列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
     @PostMapping(value = "/list" )
     public List<HotelOrder> getHotelListByQuery(@RequestBody HotelOrder query)throws Exception{
     	return hotelOrderService.getListByQuery(query);
     }
	/**
	 * <b>保存订单</b>
	 * @param hotelOrder
	 * @return
	 */
	@PostMapping(value = "/save")
	public boolean save(@RequestBody HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.save(hotelOrder);
	}

	/**
	 * <b>修改订单</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	public boolean update(@RequestBody HotelOrder query)throws Exception{
		return hotelOrderService.update(query);
	}

	/**
	 * <b>根据订单Id查询订单</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	public HotelOrder getHotelOrderById(@RequestParam Long orderId)throws Exception{
		return hotelOrderService.getHotelOrderById(orderId);
	}
	/**
	 * <b根据订单编号查询订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/no")
	public HotelOrder getHotelOrderByNo(@RequestParam String orderNo) throws Exception {
		return hotelOrderService.getHotelOrderByNo(orderNo);
	}
}
