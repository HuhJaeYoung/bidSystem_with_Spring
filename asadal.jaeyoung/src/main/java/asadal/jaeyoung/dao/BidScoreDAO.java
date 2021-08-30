package asadal.jaeyoung.dao;

import java.util.List;
import java.util.Map;

import asadal.jaeyoung.vo.BidScoreVO;

public interface BidScoreDAO {
	public void bidScoreInsert(BidScoreVO bidScoreVO) throws Exception;
	
	public int getMinPrice(int bid_id) throws Exception;
	
	public List<BidScoreVO> bidListNoTotal(int bid_id) throws Exception;
	
	public void bidTotalScore(BidScoreVO bidScoreVO) throws Exception;
	
	public List<BidScoreVO> bidList(int bid_id) throws Exception;
	
	public BidScoreVO bidCompanySelect(int score_id) throws Exception;
	
	public void bidTechScore(BidScoreVO bidScoreVO) throws Exception;
	
	public void insertFile(Map<String, Object> map) throws Exception;
	
	public List<Map<String,Object>> selectFileList(int score_id) throws Exception;
	
	public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception;

}
