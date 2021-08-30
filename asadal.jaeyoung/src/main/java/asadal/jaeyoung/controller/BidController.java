package asadal.jaeyoung.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import asadal.jaeyoung.paging.PageMaker;
import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.service.BidScoreService;
import asadal.jaeyoung.service.BidService;
import asadal.jaeyoung.vo.BidScoreVO;
import asadal.jaeyoung.vo.BidVO;


@Controller
public class BidController {
	private static final Logger logger = LoggerFactory.getLogger(BidController.class);
	@Inject
	private BidService bidService;
	
	@Inject
	private BidScoreService bidScoreService;
	
	@RequestMapping(value = "/bid/writeView",method=RequestMethod.GET)
	public String bidWriteView() throws Exception{
		logger.info("bidwriteView");
		return "bid/writeView";
	}
	@RequestMapping(value="/bid/write",method=RequestMethod.POST)
	public String bidWrite(BidVO bidVO, MultipartHttpServletRequest bidRequest) throws Exception{
		logger.info("bidwrite");
		
		bidService.bidWrite(bidVO,bidRequest);
		return "redirect:/bid/list";
	}
	@RequestMapping(value="/bid/list",method=RequestMethod.GET)
	public String bidList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("list");
		
		model.addAttribute("bidList",bidService.bidList(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(bidService.bidListCount(scri));
		model.addAttribute("pageMaker",pageMaker);
		return "bid/bidList";
	}
	@RequestMapping(value="/bid/endList",method=RequestMethod.GET)
	public String bidEndList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("list");
		
		model.addAttribute("bidEndList",bidService.bidEndList(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(bidService.bidListCount(scri));
		model.addAttribute("pageMaker",pageMaker);
		return "bid/bidEndList";
	}
	
	@RequestMapping(value="/bid/detail",method=RequestMethod.GET)
	public String bidRead(BidVO bidVO,@ModelAttribute("scri") SearchCriteria scri,Model model) throws Exception{
		logger.info("read");
		
		model.addAttribute("bidRead",bidService.bidRead(bidVO.getBid_id()));
		model.addAttribute("scri",scri);
		
		List<Map<String,Object>> fileList=bidService.selectFileList(bidVO.getBid_id());
		model.addAttribute("file",fileList);
		
		return "bid/detail";
	}
	@RequestMapping(value="/bid/updateView",method=RequestMethod.GET)
	public String bidUpdateView(BidVO bidVO,@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("updateView");
		
		model.addAttribute("bidUpdate",bidService.bidRead(bidVO.getBid_id()));
		model.addAttribute("scri",scri);
		
		List<Map<String,Object>> fileList=bidService.selectFileList(bidVO.getBid_id());
		model.addAttribute("file",fileList);
		
		return "bid/updateView";
	}
	@RequestMapping(value = "/bid/update",method=RequestMethod.POST)
	public String bidUpdate(BidVO bidVO,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr,
			@RequestParam(value="fileNoDel[]") String[] files,
			 @RequestParam(value="fileNameDel[]") String[] fileNames,
			 MultipartHttpServletRequest mpRequest) throws Exception{
		logger.info("update");
		
		bidService.bidUpdate(bidVO,files,fileNames,mpRequest);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/bid/list";
	}
	@RequestMapping(value="/bid/delete", method=RequestMethod.POST)
	public String bidDelete(BidVO bidVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("delete");
		
		bidService.bidDelete(bidVO.getBid_id());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/bid/list";
	}
	//입찰
	@RequestMapping(value="/bid/bidStartView",method=RequestMethod.GET)
	public String startBidView(BidScoreVO bidScoreVO,Model model,SearchCriteria scri) throws Exception{
		logger.info("bidScoreInsert");
		model.addAttribute("bidStart",bidService.bidRead(bidScoreVO.getBid_id()));
		model.addAttribute("scri",scri);
		

		return "bid/bidStartView";
	}
	@RequestMapping(value="/bid/startBid",method=RequestMethod.POST)
	public String startBid(BidScoreVO bidScoreVO,SearchCriteria scri, RedirectAttributes rttr,MultipartHttpServletRequest bidScoreRequest) throws Exception{
		logger.info("bidScoreInsert");
		
		
		bidScoreService.bidScoreInsert(bidScoreVO,bidScoreRequest);
		
		rttr.addAttribute("bid_id", bidScoreVO.getBid_id());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/bid/list";
	}

	/*
	 * @RequestMapping(value="/bid/endBid",method=RequestMethod.POST) public String
	 * endBid(BidVO bidVO,@ModelAttribute("scri") SearchCriteria scri,
	 * RedirectAttributes rttr) throws Exception{
	 * logger.info("bidTotalScoreInsert");
	 * 
	 * bidScoreService.bidCalculateTotalScore(bidVO.getBid_id());
	 * rttr.addAttribute("page", scri.getPage()); rttr.addAttribute("perPageNum",
	 * scri.getPerPageNum()); rttr.addAttribute("searchType", scri.getSearchType());
	 * rttr.addAttribute("keyword", scri.getKeyword()); return "redirect:/bid/list";
	 * }
	 */
	@RequestMapping(value="/bid/endBid",method=RequestMethod.POST)
	public String endBid (BidVO bidVO,SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		bidService.endBid(bidVO);
		rttr.addAttribute("Bid_id", bidVO.getBid_id());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/bid/detail";
	}
	@RequestMapping(value="/bid/evaluationEnd",method=RequestMethod.POST)
	public String evaluationEnd(BidVO bidVO) throws Exception{
		logger.info("bidTotalScoreInsert");

		bidScoreService.bidCalculateTotalScore(bidVO.getBid_id());

		return "redirect:/bid/endList";
	}
	@RequestMapping(value="/bid/bidCompanyList",method = RequestMethod.GET)
	public String bidCompanyList(Model model,BidVO bidVO) throws Exception{
		logger.info("listCompany");
		model.addAttribute("bidName",bidService.bidRead(bidVO.getBid_id()));
		List<BidScoreVO> bidScoreList= bidScoreService.bidList(bidVO.getBid_id());
		model.addAttribute("bidCompanyList",bidScoreList);
		return "bid/bidCompanyList";
	}
	
	@RequestMapping(value="/bid/techScoreInsertView",method=RequestMethod.GET)
	public String techScoreInsertView(Model model,BidScoreVO bidScoreVO) throws Exception{
		
		model.addAttribute("techScore",bidScoreService.bidCompanySelect(bidScoreVO.getScore_id()));
		
		List<Map<String,Object>> fileList=bidScoreService.selectFileList(bidScoreVO.getScore_id());
		model.addAttribute("file",fileList);
		return "bid/techScoreInsertView";
	}
	@RequestMapping(value="/bid/techScoreInsert",method=RequestMethod.POST)
	public String techScoreInsert(BidScoreVO bidScoreVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		
		bidScoreService.bidTechScore(bidScoreVO);
		rttr.addAttribute("Bid_id", bidScoreVO.getBid_id());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/bid/bidCompanyList";
	}
	//첨부파일 다운
	@RequestMapping(value="/bid/techInserView/fileDown")
	public void techScoreFileDown(@RequestParam Map<String,Object> map, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = bidScoreService.selectFileInfo(map);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
	
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\jaeyoung\\file\\techFile\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
	@RequestMapping(value="/bid/detail/fileDown")
	public void bidDetailFileDown(@RequestParam Map<String,Object> map, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = bidService.selectFileInfo(map);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
	
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\jaeyoung\\file\\bidFile\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
	
	
	//입찰 종료 후 점수 순위별 공개
	@RequestMapping(value="/bid/companyList",method = RequestMethod.GET)
	public String companyList(Model model,BidVO bidVO) throws Exception{
		logger.info("listCompany");
		model.addAttribute("bidName",bidService.bidRead(bidVO.getBid_id()));
		List<BidScoreVO> bidScoreList= bidScoreService.bidList(bidVO.getBid_id());
		model.addAttribute("bidCompanyList",bidScoreList);
		return "bid/companyList";
	}
}
