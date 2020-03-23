package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.enums.OrderStatusEnum;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.entity.HotelOrder;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.entity.UserLinkUser;
import cn.ekgc.itrip.pojo.vo.AddHotelOrderVO;
import cn.ekgc.itrip.pojo.vo.HotelRoom;
import cn.ekgc.itrip.pojo.vo.RoomStoreVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import cn.ekgc.itrip.transport.HotelOrderTransport;
import cn.ekgc.itrip.transport.HotelRoomTransport;
import cn.ekgc.itrip.transport.HotelTransport;
import cn.ekgc.itrip.transport.UserTransport;
import cn.ekgc.itrip.util.HotelOrderNoCreator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-主业务酒店订单模块控制器</b>
 */
@RestController("hotelOrderController")
@RequestMapping("/biz/api/hotelorder")
public class HotelOrderController extends BaseController {
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private HotelRoomTransport hotelRoomTransport;
	@Autowired
	private UserTransport userTransport;
	@Autowired
	private HotelOrderTransport hotelOrderTransport;
	/**
	 * <b>生成订单前，获得预定信息</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getpreorderinfo")
	public ResponseDto<Object> getPreOrderInfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO)throws Exception{
     RoomStoreVO roomStoreVO = new RoomStoreVO();

     //根据 hotelId 查询对应的Hotel对象,获得id和酒店名称
		Hotel hotel = hotelTransport.getHotelById(validateRoomStoreVO.getHotelId());
		roomStoreVO.setHotelId(hotel.getId());
		roomStoreVO.setHotelName(hotel.getHotelName());

		//根据roomId查询对应的HotelRoom对象,获得酒店房间id和房间价格
		//在收费区不用展示酒店房间昵称，根据房间Id查询房间价格即可
		HotelRoom hotelRoom = hotelRoomTransport.queryHotelRoomById(validateRoomStoreVO.getRoomId());
		roomStoreVO.setRoomId(hotelRoom.getId());
		roomStoreVO.setPrice(hotelRoom.getRoomPrice());

		//根据入住时间和退房时间，查询该房间所剩数量
		int store = hotelRoomTransport.queryHotelRoomStoreByDate(validateRoomStoreVO);
		roomStoreVO.setStore(store);

		roomStoreVO.setCheckInDate(validateRoomStoreVO.getCheckInDate());
		roomStoreVO.setCheckOutDate(validateRoomStoreVO.getCheckOutDate());
		//在这个时间段所订出去的房间数量
		roomStoreVO.setCount(validateRoomStoreVO.getCount());

		return ResponseDto.success(roomStoreVO);
	}

	/**
		 * <b>生产订单</b>
		 */
		@PostMapping(value = "/addhotelorder")
		public ResponseDto<Object> addHotelOrder(@RequestBody AddHotelOrderVO addHotelOrderVO)throws Exception{
			//查询此时是否有房
			ValidateRoomStoreVO validateRoomStoreVO = new ValidateRoomStoreVO();
			//BeansUtils用于封装数据
			BeanUtils.copyProperties(addHotelOrderVO,validateRoomStoreVO);
			int store = hotelRoomTransport.queryHotelRoomStoreByDate(validateRoomStoreVO);
            //如果库存数量大于订房数量
			if (store >= addHotelOrderVO.getCount()){
				//在有房的情况下，保存订单数据表
				//创建HotelOrder对象
				String userCode = "";
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies){
					if ("user".equals(cookie.getName())){
						userCode = cookie.getValue();
					}
				}
				User userquery = new User();
				userquery.setUserCode(userCode);

				User user = userTransport.getListByQuery(userquery).get(0);

				HotelOrder hotelOrder = new HotelOrder();
				//将user中的用户Id传入hotelOrder中
				hotelOrder.setUserId(user.getId());
				//再将hotelOrder中的信息传入addHotelOrderVo中
				BeanUtils.copyProperties(addHotelOrderVO,hotelOrder);
				//拼接字符串
				String orderNo = HotelOrderNoCreator.createHotelOrderNo(addHotelOrderVO.getHotelId(),addHotelOrderVO.getRoomId());
				//生成订单号
				hotelOrder.setOrderNo(orderNo);
				//交易编号
				hotelOrder.setTradeNo(orderNo);
                //订单状态
				hotelOrder.setOrderStatus(OrderStatusEnum.ORDER_STATUS_PREPAY.getCode());
				//订单价格
				HotelRoom hotelRoom = hotelRoomTransport.queryHotelRoomById(addHotelOrderVO.getRoomId());
                //订房数量 * 房间费用 = 总金额
                hotelOrder.setPayAmount(addHotelOrderVO.getCount() * hotelRoom.getRoomPrice());
                //添加联系人信息
                List<UserLinkUser> userLinkUserList = addHotelOrderVO.getLinkUser();
                StringBuffer sb = new StringBuffer();
                for (UserLinkUser userLinkUser : userLinkUserList){
                	sb.append(userLinkUser.getLinkUserName() + ",");
			}
                hotelOrder.setLinkUserName(sb.toString().substring(0,sb.toString().length() - 1 ));
                //保存订单
				hotelOrderTransport.save(hotelOrder);

                //获得 HotelOrder 对象的 id和 orderId添加为 Map集合
				//查询对象
				HotelOrder query = new HotelOrder();
				query.setOrderNo(orderNo);
				HotelOrder order = hotelOrderTransport.getHotelOrderByNo(orderNo);

				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("id",order.getId());
				resultMap.put("orderNo",order.getOrderNo());
				//返回该Map集合
				return  ResponseDto.success(resultMap);

			}

			//获得 hotelOrder 对象的 Id 和 OrderId 添加为Map集合
			return null;
	}
}
