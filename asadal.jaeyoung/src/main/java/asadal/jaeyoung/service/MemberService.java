package asadal.jaeyoung.service;

import java.util.List;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.MemberVO;

public interface MemberService {
	
	public void memberRegister(MemberVO memberVO) throws Exception;
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception;
	
	public List<MemberVO>memberList(SearchCriteria scri) throws Exception;
	
	public int memberListCount(SearchCriteria scri) throws Exception;
	
	public void memberUpdate(MemberVO memberVO) throws Exception;
	
	public void memberDelete(MemberVO memberVO) throws Exception;
	
	public int memberIdChk(MemberVO memberVO) throws Exception;
	
	public MemberVO getByUserId(String userId) throws Exception;
	
	public String getByUserPass(String userId) throws Exception;

}
