package asadal.jaeyoung.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.ScheduleVO;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO{

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<ScheduleVO> showSchedule() throws Exception{
		return sqlSession.selectList("scheduleMapper.showSchedule");
	}
	@Override
	public void addSchedule(ScheduleVO scheduleVO) throws Exception{
		sqlSession.insert("scheduleMapper.addSchedule",scheduleVO);
	}
	@Override
	public void deleteSchedule(int sch_id) throws Exception{
		sqlSession.delete("scheduleMapper.deleteSchedule",sch_id);
	}
	
	@Override
	public List<ScheduleVO> scheduleList(SearchCriteria scri) throws Exception{
		return sqlSession.selectList("scheduleMapper.scheduleListPage",scri);
	}
	@Override
	public int scheduleListCount(SearchCriteria scri) throws Exception{
		return sqlSession.selectOne("scheduleMapper.listCount",scri);
	}
}
