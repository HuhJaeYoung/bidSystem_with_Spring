package asadal.jaeyoung.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import asadal.jaeyoung.service.MemberService;
import asadal.jaeyoung.vo.MemberVO;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService memberService;
	
	//회원가입 get
	@RequestMapping(value="/member/signUpView", method= RequestMethod.GET)
	public void signUpView() throws Exception{
		logger.info("signupView");
	}
	
	
	@RequestMapping(value="/member/signUp", method=RequestMethod.POST)
	public String signUp(MemberVO memberVO) throws Exception{
		logger.info("signUp");
		int result=memberService.memberIdChk(memberVO);
		try {
			
			if(result==1) {
				return "/member/signUpView";
			}else if(result==0) {
				memberService.memberRegister(memberVO);

			}
		}catch (Exception e) {
			throw new RuntimeException();
		}
	
		return "redirect:/";
	}
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String memberLoginForm() throws Exception{
		return "member/loginForm";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String memberLogin(MemberVO memberVO,HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		HttpSession session = req.getSession();
		
		MemberVO login = memberService.memberLogin(memberVO);

	
		if((login == null)||(login.getEnabled()==0)) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			return "member/loginForm";
		}else {
			session.setAttribute("member", login);
			System.out.println(login);
		}
		
		return "redirect:/";
		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String memberLogout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/member/updateView",method = RequestMethod.GET)
	public String memberUpdateView() throws Exception{
		return "member/updateView";
	}
	@RequestMapping(value="/member/update",method = RequestMethod.POST)
	public String memberUpdate(MemberVO memberVO, HttpSession session) throws Exception{
		memberService.memberUpdate(memberVO);
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value="/member/memberDeleteView",method=RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "member/memberDeleteView";
	}

	@RequestMapping(value="/member/memberDelete",method=RequestMethod.POST)
	public String memberDelete(MemberVO memberVO, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		
		String sessionPass = member.getUserPass();
		
		String voPass= memberVO.getUserPass();
		
		
		if(!(sessionPass.equals(voPass))) {
			rttr.addFlashAttribute("msg",false);
			return "redirect:/member/memberDeleteView";
		}
		memberService.memberDelete(memberVO);
		session.invalidate();
		return "redirect:/";
	}
	
	//ID중복체크
	@ResponseBody
	@RequestMapping(value="/member/memberIdChk",method=RequestMethod.POST)
	public int memberIdChk(MemberVO memberVO) throws Exception{
		int result = memberService.memberIdChk(memberVO);
		return result;
	}
}
