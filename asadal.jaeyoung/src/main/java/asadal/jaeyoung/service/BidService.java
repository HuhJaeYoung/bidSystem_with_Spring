package asadal.jaeyoung.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.BidVO;


public interface BidService {
	public void bidWrite(BidVO bidVO,MultipartHttpServletRequest bidRequest) throws Exception;
	public List<BidVO> bidList(SearchCriteria scri) throws Exception;
	
	public int bidListCount(SearchCriteria scri) throws Exception;
	
	public BidVO bidRead(int bid_id) throws Exception;
	
	public void bidUpdate(BidVO bidVO,String[] files, String[] fileNames,MultipartHttpServletRequest mpRequest) throws Exception;
	
	public void bidDelete(int bid_id) throws Exception;
	
	public void endBid(BidVO bidVO) throws Exception;
	
	public List<BidVO> bidEndList(SearchCriteria scri) throws Exception;

	public List<Map<String, Object>> selectFileList(int bid_id) throws Exception;
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;

}
