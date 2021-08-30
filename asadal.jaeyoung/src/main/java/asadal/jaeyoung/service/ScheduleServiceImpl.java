package asadal.jaeyoung.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import asadal.jaeyoung.dao.ScheduleDAO;
import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.ScheduleVO;

@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Inject
	private ScheduleDAO scheduleDAO;
	
	@Override
	public List<ScheduleVO> showSchedule() throws Exception{
		return scheduleDAO.showSchedule();
	}
	
	@Override
	public void addSchedule(ScheduleVO scheduleVO) throws Exception{
		scheduleDAO.addSchedule(scheduleVO);
	}
	
	@Override
	public void deleteSchedule(int sch_id) throws Exception{
		scheduleDAO.deleteSchedule(sch_id);
	}
	@Override
	public List<ScheduleVO> scheduleList(SearchCriteria scri) throws Exception{
		return scheduleDAO.scheduleList(scri);
	}
	@Override
	public int scheduleListCount(SearchCriteria scri) throws Exception{
		return scheduleDAO.scheduleListCount(scri);
	}
}
