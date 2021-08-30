package asadal.jaeyoung.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BidVO {

	private int bid_id;
	private String bid_title;
	private String bid_content;
	private String bid_writer;
	private long bid_price;
	private Date regdate;
	private int bid_status;
	//bid_status가 0이면 진행중 1이면 종료
	
}
