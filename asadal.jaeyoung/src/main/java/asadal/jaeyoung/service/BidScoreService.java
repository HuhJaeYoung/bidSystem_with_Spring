package asadal.jaeyoung.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import asadal.jaeyoung.vo.BidScoreVO;

public interface BidScoreService {

	public void bidScoreInsert(BidScoreVO bidScoreVO, MultipartHttpServletRequest bidScoreRequest) throws Exception;

	public void bidCalculateTotalScore(int bid_id) throws Exception;
	
	public List<BidScoreVO> bidList(int bid_id) throws Exception;
	
	public BidScoreVO bidCompanySelect(int score_id) throws Exception;
	
	public void bidTechScore(BidScoreVO bidScoreVO) throws Exception;
	
	public List<Map<String,Object>> selectFileList(int score_id) throws Exception;
	
	public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception;

	
}
