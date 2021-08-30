package asadal.jaeyoung.vo;

import lombok.Data;

@Data
public class BidScoreVO {

	private int score_id;
	private int bid_id;
	private int price;
	private int tech_score;
	private double total_score;
	private String company_name;
}
