package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.vo.HotelRoom;
import cn.ekgc.itrip.pojo.vo.SearchHotelRoomVO;

import java.util.List;

/**
 * <b>爱旅行-酒店房间模块业务层接口</b>
 */
public interface HotelRoomService {
	/**
	 * <b>查询酒店列表-此刻可以预定的房间列表</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	List<HotelRoom> queryHotelRoomByHotel(SearchHotelRoomVO searchHotelRoomVO)throws Exception;
}