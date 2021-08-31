package asadal.jaeyoung.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import asadal.jaeyoung.paging.PageMaker;
import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.security.AES256;
import asadal.jaeyoung.service.MemberService;
import asadal.jaeyoung.service.ScheduleService;
import asadal.jaeyoung.vo.MemberVO;

@Controller
public class AdminController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	AES256 aes256 = new AES256();
	
	@RequestMapping(value="/admin/index",method=RequestMethod.GET)
	public String adminIndex(HttpSession session) {
		MemberVO hasSessionMemberVO = (MemberVO)session.getAttribute("member");
		
		if((hasSessionMemberVO==null) || (!hasSessionMemberVO.getAuth().equals("ROLE_ADMIN"))) {
			return "redirect:/";
		}
		else {
		System.out.println(hasSessionMemberVO);
		return "admin/index";
		}
	}
	@RequestMapping(value="/admin/memberList",method=RequestMethod.GET)
	public String noticeList(HttpSession session,Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		MemberVO hasSessionMemberVO = (MemberVO)session.getAttribute("member");
				if((hasSessionMemberVO==null) || (!hasSessionMemberVO.getAuth().equals("ROLE_ADMIN"))) {
			return "redirect:/";
		}
		else {
			
			
		model.addAttribute("memberList",memberService.memberList(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(memberService.memberListCount(scri));
		model.addAttribute("pageMaker",pageMaker);
		return "admin/memberList";
		}
	}
	
	@RequestMapping(value="/admin/updateView",method = RequestMethod.GET)
	public String adminUpdateView(@RequestParam String userId,Model model,HttpSession session) throws Exception{
		MemberVO hasSessionMemberVO = (MemberVO)session.getAttribute("member");
		if((hasSessionMemberVO==null) || (!hasSessionMemberVO.getAuth().equals("ROLE_ADMIN"))) {
			return "redirect:/";
		}else {
		MemberVO userVO = memberService.getByUserId(userId);
		userVO.setPhoneNum(aes256.decrypt(userVO.getPhoneNum()));
		userVO.setEmail(aes256.decrypt(userVO.getEmail()));
		
		model.addAttribute("updateUser", userVO);
		return "admin/updateView";
		}
	}
	@RequestMapping(value="/admin/memberUpdate",method=RequestMethod.POST)
	public String adminMemberUpdate(MemberVO updateUserVO) throws Exception{
		memberService.memberUpdate(updateUserVO);
		return "redirect:/admin/memberList";
	}
	@RequestMapping(value="/admin/adminSchedule", method=RequestMethod.GET)
	public String adminSchedule(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		
		model.addAttribute("scheduleList",scheduleService.scheduleList(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(scheduleService.scheduleListCount(scri));
		model.addAttribute("pageMaker",pageMaker);
	
		return "admin/adminSchedule";
	}
	
	@RequestMapping(value="/admin/scheduleDelete",method=RequestMethod.POST)
	public String scheduleDelete(@RequestParam int sch_id) throws Exception{
		
		scheduleService.deleteSchedule(sch_id);
		return "redirect:/";
	}

}
