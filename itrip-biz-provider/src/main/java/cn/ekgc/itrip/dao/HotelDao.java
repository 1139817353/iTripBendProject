package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>爱旅行-酒店功能数据持久层</b>
 */
@Repository
public interface HotelDao {
	public List<Hotel> findListByQuery(Hotel query) throws Exception;
}
