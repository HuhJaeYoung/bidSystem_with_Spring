package asadal.jaeyoung.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import asadal.jaeyoung.vo.BidScoreVO;

@Repository
public class BidScoreDAOImpl implements BidScoreDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void bidScoreInsert(BidScoreVO bidScoreVO) throws Exception{
		sqlSession.insert("bidScoreMapper.bidScoreInsert",bidScoreVO);
	}
	
	@Override
	public int getMinPrice(int bid_id) throws Exception{
		return sqlSession.selectOne("bidScoreMapper.getMinPrice",bid_id);
	}
	@Override
	public List<BidScoreVO> bidListNoTotal(int bid_id) throws Exception{
		return sqlSession.selectList("bidScoreMapper.bidListNoTotal",bid_id);
	}
	
	@Override
	public void bidTotalScore(BidScoreVO bidScoreVO) throws Exception{
		sqlSession.update("bidScoreMapper.bidTotalScore",bidScoreVO);
	}
	
	@Override
	public List<BidScoreVO> bidList(int bid_id) throws Exception{
		return sqlSession.selectList("bidScoreMapper.bidList",bid_id);
	}

	@Override
	public BidScoreVO bidCompanySelect(int score_id) throws Exception{
		return sqlSession.selectOne("bidScoreMapper.bidCompanySelect",score_id);
	}
	@Override
	public void bidTechScore(BidScoreVO bidScoreVO) throws Exception{
		sqlSession.update("bidScoreMapper.bidTechScore",bidScoreVO);
	}
	
	@Override
	public void insertFile(Map<String, Object> map) throws Exception{
		sqlSession.insert("bidScoreMapper.insertFile",map);
	}
	@Override
	public List<Map<String,Object>> selectFileList(int score_id) throws Exception{
		return sqlSession.selectList("bidScoreMapper.selectFileList",score_id);
	}
	@Override
	public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception{
		return sqlSession.selectOne("bidScoreMapper.selectFileInfo",map);
	}

}
