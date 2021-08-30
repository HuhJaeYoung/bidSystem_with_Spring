package asadal.jaeyoung.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import asadal.jaeyoung.dao.MemberDAO;
import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO memberDAO;
	
	@Override
	public void memberRegister(MemberVO memberVO) throws Exception{
		memberDAO.memberRegister(memberVO);
	}
	
	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		
		
		return memberDAO.memberLogin(memberVO);
		
	}
	
	@Override
	public void memberUpdate(MemberVO memberVO) throws Exception{
		memberDAO.memberUpdate(memberVO);
	}
	@Override
	public void memberDelete(MemberVO memberVO) throws Exception{
		
		memberDAO.memberDelete(memberVO);
	}
	@Override
	public int memberIdChk(MemberVO memberVO) throws Exception{
		int result=memberDAO.memberIdChk(memberVO);
		return result;
	}
	
	@Override
	public List<MemberVO> memberList(SearchCriteria scri) throws Exception{
		return memberDAO.memberList(scri);
	}
	@Override
	public int memberListCount(SearchCriteria scri) throws Exception{
		return memberDAO.memberListCount(scri);
	}
	
	@Override
	public MemberVO getByUserId(String userId) throws Exception{
		return memberDAO.getByUserId(userId);
	}
	
}
