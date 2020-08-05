package com.xiaoyuer.hn.admin.dao;

import java.io.Serializable;

/**
 *   单独类使用，无需引入整个core-dao
 *   排除ids-client.IdsAuthInterceptor 中的core-dao后，最后将使用该同名的dmo
 * @author xiaoyuer
 *
 */
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 8071773107312149213L;

	private Integer id;

	private String userCode;


	private String cellPhone;

	private String userName;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	private Integer age;

	private String nickName;



	

}
