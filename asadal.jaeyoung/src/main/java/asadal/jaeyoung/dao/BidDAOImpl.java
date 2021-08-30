package asadal.jaeyoung.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.BidVO;


@Repository
public class BidDAOImpl implements BidDAO{
	
	@Inject
	private SqlSession sqlSession;

	@Override
    public void bidWrite(BidVO bidVO) throws Exception{
		sqlSession.insert("bidMapper.bidInsert",bidVO);
	}
	
	@Override
	public List<BidVO> bidList(SearchCriteria scri) throws Exception{
		return sqlSession.selectList("bidMapper.bidListPage",scri);
	}
	@Override
	public int bidListCount(SearchCriteria scri) throws Exception{
		return sqlSession.selectOne("bidMapper.bidListCount",scri);
	}
	
	@Override 
	public BidVO bidRead(int bid_id) throws Exception{
		return sqlSession.selectOne("bidMapper.read", bid_id);
	}
	
	@Override
	public void bidUpdate(BidVO bidVO) throws Exception{
		 sqlSession.update("bidMapper.update", bidVO);
	}
	
	@Override
	public void bidDelete(int bid_id) throws Exception{
		sqlSession.delete("bidMapper.delete", bid_id);
	}
	@Override
	public void endBid(BidVO bidVO) throws Exception{
		sqlSession.update("bidMapper.endBid",bidVO);
	}
	@Override
	public List<BidVO> bidEndList(SearchCriteria scri) throws Exception{
		return sqlSession.selectList("bidMapper.bidEndListPage",scri);
	}
	
	@Override
	public void insertFile(Map<String, Object> map) throws Exception{
		sqlSession.insert("bidMapper.insertFile",map);
	}
	@Override
	public List<Map<String,Object>> selectFileList(int bid_id) throws Exception{
		return sqlSession.selectList("bidMapper.selectFileList",bid_id);
	}
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("bidMapper.selectFileInfo", map);
	}
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.update("bidMapper.updateFile", map);
	}
}
