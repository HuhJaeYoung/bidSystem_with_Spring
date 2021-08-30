package asadal.jaeyoung.vo;

import lombok.Data;

@Data
public class ScheduleVO {
	private int sch_id;
	private String subject;
	private String startDate;
	private String endDate;
	private String memo;

}
