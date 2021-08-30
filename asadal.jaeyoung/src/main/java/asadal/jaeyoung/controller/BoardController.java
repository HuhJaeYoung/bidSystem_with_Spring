package asadal.jaeyoung.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import asadal.jaeyoung.paging.PageMaker;
import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.service.BoardService;
import asadal.jaeyoung.service.ReplyService;
import asadal.jaeyoung.vo.BoardVO;
import asadal.jaeyoung.vo.MemberVO;
import asadal.jaeyoung.vo.ReplyVO;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService boardService;
	
	@Inject
	ReplyService replyService;
	
	//글쓰기
	
	@RequestMapping(value = "/board/writeView",method=RequestMethod.GET)
	public String writeView() throws Exception{
		logger.info("writeView");
		return "board/writeView";
	}
	
	@RequestMapping(value="/board/write",method=RequestMethod.POST)
	public String write(BoardVO boardVO, HttpSession session) throws Exception{
		logger.info("write");
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		String writer = memberVO.getUserId();
		boardService.boardWrite(boardVO,writer);
		return "redirect:/board/list";
	}
	
	//목록조회
	@RequestMapping(value="/board/list",method=RequestMethod.GET)
	public String boardList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("list");
		
		model.addAttribute("boardList",boardService.boardList(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardService.boardListCount(scri));
		model.addAttribute("pageMaker",pageMaker);
		return "board/boardList";
	}
	
	@RequestMapping(value="/board/detail",method=RequestMethod.GET)
	public String boardRead(BoardVO boardVO,@ModelAttribute("scri") SearchCriteria scri,Model model) throws Exception{
		logger.info("read");
		
		model.addAttribute("boardRead",boardService.boardRead(boardVO.getBoard_id()));
		model.addAttribute("scri",scri);
		
		List<ReplyVO> replyList= replyService.replyRead(boardVO.getBoard_id());
		model.addAttribute("replyList",replyList);
		return "board/detail";
	}
	@RequestMapping(value="/board/updateView",method=RequestMethod.GET)
	public String boardUpdateView(BoardVO boardVO,@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("updateView");
		
		model.addAttribute("boardUpdate",boardService.boardRead(boardVO.getBoard_id()));
		model.addAttribute("scri",scri);
		
		return "board/updateView";
	}
	@RequestMapping(value = "/board/update",method=RequestMethod.POST)
	public String boardUpdate(BoardVO boardVO,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("update");
		
		boardService.boardUpdate(boardVO);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/list";
	}
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String boardDelete(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("delete");
		
		boardService.boardDelete(boardVO.getBoard_id());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	//댓글작성
	@RequestMapping(value="/board/replyWrite",method=RequestMethod.POST)
	public String replyWrite(ReplyVO replyVO,HttpSession session,SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("replyWrite");
		
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String replyWriter = memberVO.getUserId();
		replyService.replyWrite(replyVO,replyWriter);
		
		rttr.addAttribute("board_id", replyVO.getBoard_id());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/board/detail";
	}
	
	//댓글 수정 GET
	@RequestMapping(value="/board/replyUpdateView",method=RequestMethod.GET)
	public String replyUpdateView(ReplyVO replyVO,SearchCriteria scri,Model model) throws Exception{
		logger.info("replyWrite");
		
		model.addAttribute("replyUpdate", replyService.replySelect(replyVO.getReply_id()));
		model.addAttribute("scri",scri);
		
		return "board/replyUpdateView";
	}
	
	//댓글수정 POST 
	@RequestMapping(value="/board/replyUpdate",method=RequestMethod.POST)
	public String replyUpdate(ReplyVO replyVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("reply write");
		
		replyService.replyUpdate(replyVO);
		rttr.addAttribute("board_id", replyVO.getBoard_id());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/detail";
		
	}
	
	//댓글삭제 GET
	@RequestMapping(value="/board/replyDeleteView",method=RequestMethod.GET)
	public String replyDeleteView(ReplyVO replyVO, SearchCriteria scri,Model model) throws Exception{
		logger.info("replyWrite");
		
		model.addAttribute("replyDelete",replyService.replySelect(replyVO.getReply_id()));
		model.addAttribute("scri",scri);
		
		return "board/replyDeleteView";
	}
	//댓글삭제 POST
	@RequestMapping(value="/board/replyDelete",method=RequestMethod.POST)
	public String replyDelete(ReplyVO replyVO,SearchCriteria scri,RedirectAttributes rttr) throws Exception{
		logger.info("replyWrite");
		
		replyService.replyDelete(replyVO);
		rttr.addAttribute("board_id", replyVO.getBoard_id());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/detail";
	}

}
