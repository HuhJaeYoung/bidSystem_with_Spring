package asadal.jaeyoung.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

	private int board_id;
	private String board_title;
	private String board_content;
	private String board_writer;
	private Date board_regdate;
	private int hit;

}
