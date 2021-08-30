package asadal.jaeyoung.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import asadal.jaeyoung.dao.BidDAO;
import asadal.jaeyoung.dao.BidScoreDAO;
import asadal.jaeyoung.util.TechFileUtils;
import asadal.jaeyoung.vo.BidScoreVO;
import asadal.jaeyoung.vo.BidVO;

@Service
public class BidScoreServiceImpl implements BidScoreService {
	
	@Resource(name="techFileUtils")
	private TechFileUtils techFileUtils;
	@Inject
	private BidScoreDAO bidScoreDAO;
	
	@Inject 
	private BidDAO bidDAO;
	
	
	@Override
	public void bidScoreInsert(BidScoreVO bidScoreVO, MultipartHttpServletRequest bidScoreRequest) throws Exception{

		bidScoreDAO.bidScoreInsert(bidScoreVO);
		List<Map<String,Object>> list = techFileUtils.parseInsertFileInfo(bidScoreVO, bidScoreRequest);
		int size=list.size();
		for(int i =0;i<size;i++) {
			bidScoreDAO.insertFile(list.get(i));
		}
	}
	@Override
	public void bidCalculateTotalScore(int bid_id) throws Exception{
		
		BidVO bidVO = bidDAO.bidRead(bid_id);
		long getPrice= bidVO.getBid_price();
		
		List<BidScoreVO> bidList = bidScoreDAO.bidListNoTotal(bid_id);
		for(int i = 0;i<bidList.size();i++) {
			int getBidPrice= bidList.get(i).getPrice();
			int minPrice=bidScoreDAO.getMinPrice(bid_id);
			int tech_score=bidList.get(i).getTech_score();
			if(getBidPrice>=getPrice*0.8) {
				double result = (double)(10*((double)minPrice/getBidPrice))+tech_score;
				bidList.get(i).setTotal_score(result);
				bidScoreDAO.bidTotalScore(bidList.get(i));
			}
			else {
				double result=(double)((10*((double)minPrice/(getPrice*0.8)))+(2*(((double)getPrice*0.8-getBidPrice)/((double)(getPrice*0.8)-(getPrice*0.6)))))+tech_score;
				bidList.get(i).setTotal_score(result);
				bidScoreDAO.bidTotalScore(bidList.get(i));
			}
		}
	}
	@Override
	public List<BidScoreVO> bidList(int bid_id) throws Exception{
		return bidScoreDAO.bidList(bid_id);
	}
	@Override
	public BidScoreVO bidCompanySelect(int score_id) throws Exception{
		return bidScoreDAO.bidCompanySelect(score_id);
	}
	@Override
	public void bidTechScore(BidScoreVO bidScoreVO) throws Exception{
		bidScoreDAO.bidTechScore(bidScoreVO);
	}
	
	@Override
	public List<Map<String,Object>> selectFileList(int score_id) throws Exception{
		return bidScoreDAO.selectFileList(score_id);
	}

	@Override
	public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception{
		return bidScoreDAO.selectFileInfo(map);
	}

}
