package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.vo.HotelRoom;
import cn.ekgc.itrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;

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

	/**
	 * <b>根据主键查询房间信息</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	HotelRoom getHotelRoomById(Long roomId)throws Exception;

	/**
	 * <b>根据查询获得房间数量</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	int getHotelRoomStoreByDate(ValidateRoomStoreVO validateRoomStoreVO)throws Exception;
}
