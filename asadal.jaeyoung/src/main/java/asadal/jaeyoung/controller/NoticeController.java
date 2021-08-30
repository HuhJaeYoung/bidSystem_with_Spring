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
import asadal.jaeyoung.service.NoticeService;
import asadal.jaeyoung.vo.NoticeVO;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Inject
	NoticeService noticeService;
	
	//글쓰기
	
	@RequestMapping(value = "/notice/writeView",method=RequestMethod.GET)
	public String writeView() throws Exception{
		logger.info("writeView");
		return "notice/writeView";
	}
	
	@RequestMapping(value="/notice/write",method=RequestMethod.POST)
	public String write(NoticeVO noticeVO, MultipartHttpServletRequest noticeRequest) throws Exception{
		logger.info("write");
		
		noticeService.noticeWrite(noticeVO,noticeRequest);
		return "redirect:/notice/list";
	}
	
	//목록조회
	@RequestMapping(value="/notice/list",method=RequestMethod.GET)
	public String noticeList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("list");
		
		model.addAttribute("noticeList",noticeService.noticeList(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(noticeService.noticeListCount(scri));
		model.addAttribute("pageMaker",pageMaker);
		return "notice/noticeList";
	}
	
	@RequestMapping(value="/notice/detail",method=RequestMethod.GET)
	public String noticeRead(NoticeVO noticeVO,@ModelAttribute("scri") SearchCriteria scri,Model model) throws Exception{
		logger.info("read");
		
		model.addAttribute("noticeRead",noticeService.noticeRead(noticeVO.getId()));
		model.addAttribute("scri",scri);
		
		List<Map<String,Object>> fileList=noticeService.selectFileList(noticeVO.getId());
		model.addAttribute("file",fileList);
		
		return "notice/detail";
	}
	@RequestMapping(value="/notice/updateView",method=RequestMethod.GET)
	public String noticeUpdateView(NoticeVO noticeVO,@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("updateView");
		
		model.addAttribute("noticeUpdate",noticeService.noticeRead(noticeVO.getId()));
		model.addAttribute("scri",scri);
		List<Map<String,Object>> fileList=noticeService.selectFileList(noticeVO.getId());
		model.addAttribute("file",fileList);
		return "notice/updateView";
	}
	@RequestMapping(value = "/notice/update",method=RequestMethod.POST)
	public String noticeUpdate(NoticeVO noticeVO,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr,
			 @RequestParam(value="fileNoDel[]") String[] files,
			 @RequestParam(value="fileNameDel[]") String[] fileNames,
			MultipartHttpServletRequest noticeRequest) throws Exception{
		logger.info("update");
		
		noticeService.noticeUpdate(noticeVO,files,fileNames,noticeRequest);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/notice/list";
	}
	@RequestMapping(value="/notice/delete", method=RequestMethod.POST)
	public String noticeDelete(NoticeVO noticeVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("delete");
		
		noticeService.noticeDelete(noticeVO.getId());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/notice/list";
	}
	@RequestMapping(value="/notice/fileDown")
	public void fileDown(@RequestParam Map<String,Object> map, HttpServletResponse response) throws Exception{
		Map<String,Object> resultMap = noticeService.selectFileInfo(map);
		String storedFileName=(String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		System.out.println(originalFileName+"originfilename/controller");
		System.out.println(storedFileName+"storedfilename/controller");
		
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\jaeyoung\\file\\notice\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
}
