package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;

public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userCode;
	private String userPassword;
	private String userName;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}