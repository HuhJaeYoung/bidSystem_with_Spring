package asadal.jaeyoung.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private int board_id;
	private int reply_id;
	private String reply_content;
	private String reply_writer;
	private Date reply_regdate;
	
	@Override
	public String toString() {
		return "ReplyVO [board_id=" + board_id + ", reply_id=" + reply_id + ", reply_content=" + reply_content + ", reply_writer=" + reply_writer + ", reply_regdate="+ reply_regdate + "]";
	}

}
