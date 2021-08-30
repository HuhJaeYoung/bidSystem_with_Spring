package asadal.jaeyoung.vo;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {
	private int id;
	private String title;
	private String content;
	private String writer;
	private Date regdate;

}
