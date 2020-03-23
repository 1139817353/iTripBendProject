package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.entity.HotelOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.function.LongBinaryOperator;

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotelorder/trans")
public interface HotelOrderTransport {
	/**
	 * <b>查询列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	List<HotelOrder> getHotelListByQuery(@RequestBody HotelOrder query) throws Exception;
	/**
	 * <b>保存订单</b>
	 * @param hotelOrder
	 * @return
	 */
	@PostMapping(value = "/save")
	boolean save(@RequestBody HotelOrder hotelOrder)throws Exception;

	/**
	 * <b>修改订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	boolean update(@RequestBody HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>根据订单Id查询</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	HotelOrder getHotelOrderById(@RequestParam Long orderId)throws Exception;
	/**
	 * <b>根据订单编号查询</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/no")
	HotelOrder getHotelOrderByNo(@RequestParam String orderNo)throws Exception;
}
