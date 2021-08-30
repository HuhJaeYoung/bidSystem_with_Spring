package asadal.jaeyoung.dao;

import java.util.List;
import java.util.Map;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.BidVO;


public interface BidDAO {
	
	public void bidWrite(BidVO bidVO) throws Exception;
	//목록
	public List<BidVO> bidList(SearchCriteria scri) throws Exception;
	
	//총 개수 게시물
	public int bidListCount(SearchCriteria scri) throws Exception;
	
	
	public BidVO bidRead(int bid_id) throws Exception;
	
	public void bidUpdate(BidVO bidVO) throws Exception;
	
	public void bidDelete(int bid_id) throws Exception;
	
	public void endBid(BidVO bidVO) throws Exception;
	
	public List<BidVO> bidEndList(SearchCriteria scri) throws Exception;
	
	public void insertFile(Map<String, Object> map) throws Exception;
	
	public List<Map<String,Object>> selectFileList(int bid_id) throws Exception;
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
	public void updateFile(Map<String, Object> map) throws Exception;


}
