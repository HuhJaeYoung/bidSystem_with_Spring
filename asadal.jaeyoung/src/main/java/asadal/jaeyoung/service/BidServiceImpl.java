package asadal.jaeyoung.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import asadal.jaeyoung.dao.BidDAO;
import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.util.BidFileUtils;
import asadal.jaeyoung.vo.BidVO;

@Service
public class BidServiceImpl implements BidService{

	@Resource(name="bidFileUtils")
	private BidFileUtils bidFileUtils;
	
	@Inject
	private BidDAO bidDAO; 
	
	public void bidWrite(BidVO bidVO, MultipartHttpServletRequest bidRequest) throws Exception{
		bidDAO.bidWrite(bidVO);
	
		List<Map<String,Object>> list = bidFileUtils.parseInsertFileInfo(bidVO, bidRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			bidDAO.insertFile(list.get(i)); 
		}
	
	}
	@Override
	public List<BidVO> bidList(SearchCriteria scri) throws Exception{
		return bidDAO.bidList(scri);
	}
	@Override
	public int bidListCount(SearchCriteria scri) throws Exception{
		return bidDAO.bidListCount(scri);
	}

	@Override
	public BidVO bidRead(int bid_id) throws Exception{
		return bidDAO.bidRead(bid_id);
	}
	
	@Override
	public void bidUpdate(BidVO bidVO, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception{
		bidDAO.bidUpdate(bidVO);
		
		List<Map<String, Object>> list = bidFileUtils.parseUpdateFileInfo(bidVO, files, fileNames, mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				bidDAO.insertFile(tempMap);
			}else {
				bidDAO.updateFile(tempMap);
			}
		}
	}
	
	@Override
	public void bidDelete(int bid_id) throws Exception{
		bidDAO.bidDelete(bid_id);
	}
	@Override
	public void endBid(BidVO bidVO) throws Exception{
		bidDAO.endBid(bidVO);
	}
	@Override 
	public List<BidVO> bidEndList(SearchCriteria scri) throws Exception{
		return bidDAO.bidEndList(scri);
	}
	@Override
	public List<Map<String, Object>> selectFileList(int bid_id) throws Exception {
		// TODO Auto-generated method stub
		return bidDAO.selectFileList(bid_id);
	}
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return bidDAO.selectFileInfo(map);
	}
}
