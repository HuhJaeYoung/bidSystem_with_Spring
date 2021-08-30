package asadal.jaeyoung.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String userId;
	private String userPass;
	private String userName;
	private String phoneNum;
	private String email;
	private Date user_regDate;
	private String auth;
	private int enabled;

}
