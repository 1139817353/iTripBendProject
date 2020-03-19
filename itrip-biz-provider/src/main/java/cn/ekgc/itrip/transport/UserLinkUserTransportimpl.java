package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.UserLinkUser;
import cn.ekgc.itrip.service.UserLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController("userLinkUserTransport")
@RequestMapping("/linkuser/trans")
public class UserLinkUserTransportimpl implements UserLinkUserTransport {
	@Autowired
	private UserLinkUserService userLinkUserService;
	/**
	 * <b>根据查询信息获得查询列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	public List<UserLinkUser> queryUserLinkUserListByQuery(@RequestBody UserLinkUser query) throws Exception {
		return userLinkUserService.getUserLinkUserListByQuery(query);
	}
}
