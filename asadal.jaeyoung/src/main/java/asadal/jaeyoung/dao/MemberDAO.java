package asadal.jaeyoung.dao;

import java.util.List;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.MemberVO;

public interface MemberDAO {
	
	public void memberRegister(MemberVO memberVO) throws Exception;
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception;
	
	public void memberUpdate(MemberVO memberVO) throws Exception;
	
	public void memberDelete(MemberVO memberVO) throws Exception;
	
	public int memberIdChk(MemberVO memberVO) throws Exception;

	//목록
	public List<MemberVO> memberList(SearchCriteria scri) throws Exception;
	
	//총 개수 게시물
	public int memberListCount(SearchCriteria scri) throws Exception;
	
	//관리자가 수정할 회원 가져오기
	public MemberVO getByUserId(String userId) throws Exception;
	
}
