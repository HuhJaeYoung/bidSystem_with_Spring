package asadal.jaeyoung.service;

import java.util.List;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.ScheduleVO;

public interface ScheduleService {
	
	public List<ScheduleVO> showSchedule() throws Exception;
	
	public void addSchedule(ScheduleVO scheduleVO) throws Exception;
	
	public void deleteSchedule(int sch_id) throws Exception;

	public List<ScheduleVO> scheduleList(SearchCriteria scri) throws Exception;

	public int scheduleListCount(SearchCriteria scri) throws Exception;

}
