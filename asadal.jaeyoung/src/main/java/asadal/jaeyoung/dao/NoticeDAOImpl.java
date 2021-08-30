package asadal.jaeyoung.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.NoticeVO;

@Repository
public class NoticeDAOImpl implements NoticeDAO{

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void noticeWrite(NoticeVO noticeVO) throws Exception{
		sqlSession.insert("noticeMapper.insert",noticeVO);
	}
	
	@Override
	public List<NoticeVO> noticeList(SearchCriteria scri) throws Exception{
		return sqlSession.selectList("noticeMapper.listPage",scri);
	}
	@Override
	public int noticeListCount(SearchCriteria scri) throws Exception{
		return sqlSession.selectOne("noticeMapper.listCount",scri);
	}
	
	@Override 
	public NoticeVO noticeRead(int id) throws Exception{
		return sqlSession.selectOne("noticeMapper.read", id);
	}
	
	@Override
	public void noticeUpdate(NoticeVO noticeVO) throws Exception{
		 sqlSession.update("noticeMapper.update", noticeVO);
	}
	
	@Override
	public void noticeDelete(int id) throws Exception{
		sqlSession.delete("noticeMapper.delete", id);
	}
	
	//첨부파일 업로드
	@Override
	public void insertFile(Map<String,Object>map) throws Exception{
		sqlSession.insert("noticeMapper.insertFile",map);
	}
	//첨부파일 조회
	@Override
	public List<Map<String,Object>> selectFileList(int id) throws Exception{
		return sqlSession.selectList("noticeMapper.selectFileList",id);
	}
	//첨부파일 다운로드
	@Override
	public Map<String,Object> selectFileInfo(Map<String,Object>map) throws Exception{
		System.out.println(map+"noticeDAO");

		return sqlSession.selectOne("noticeMapper.selectFileInfo",map);
	}
	
	@Override
	public void updateFile(Map<String,Object> map) throws Exception{
		sqlSession.update("noticeMapper.updateFile",map);
	}
	
}
