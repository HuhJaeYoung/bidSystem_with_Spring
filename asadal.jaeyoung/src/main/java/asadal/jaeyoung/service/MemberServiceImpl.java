package asadal.jaeyoung.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import asadal.jaeyoung.dao.MemberDAO;
import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.security.AES256;
import asadal.jaeyoung.security.SHA512;
import asadal.jaeyoung.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO memberDAO;
	
	
	AES256 aes256 = new AES256();
	
	
	SHA512 sha512= new SHA512();
	
	@Override
	public void memberRegister(MemberVO memberVO) throws Exception{
		System.out.println(memberVO.getUserPass());
		String password = memberVO.getUserPass();
		String encode = sha512.getSha512(password);
		System.out.println(encode);
		memberVO.setUserPass(encode);
		memberVO.setEmail(aes256.encrypt(memberVO.getEmail()));
		memberVO.setPhoneNum(aes256.encrypt(memberVO.getPhoneNum()));
	
		memberDAO.memberRegister(memberVO);
	}
	
	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		String password = memberVO.getUserPass();
		String encode = sha512.getSha512(password);
		System.out.println(encode);
		memberVO.setUserPass(encode);
		System.out.println(memberDAO.memberLogin(memberVO)+"    memberService");
		if(memberDAO.memberLogin(memberVO)==null) {
			return null;
		}
		else {
		MemberVO reMemberVO = memberDAO.memberLogin(memberVO);
		System.out.println(reMemberVO +   "reMemberVO");
		reMemberVO.setUserPass(encode);
		return reMemberVO;
		}
		
	}
	
	@Override
	public void memberUpdate(MemberVO memberVO) throws Exception{
		
		if(memberVO.getUserPass()==null) {
			memberVO.setUserPass(memberDAO.getByUserPass(memberVO.getUserId()));
		}
		else {
			String password = memberVO.getUserPass();
			String encode = sha512.getSha512(password);
			System.out.println(encode);
			memberVO.setUserPass(encode);
			
		}
		memberVO.setEmail(aes256.encrypt(memberVO.getEmail()));
		memberVO.setPhoneNum(aes256.encrypt(memberVO.getPhoneNum()));
		memberDAO.memberUpdate(memberVO);
	}
	@Override
	public void memberDelete(MemberVO memberVO) throws Exception{
		String password = memberVO.getUserPass();
		String encode = sha512.getSha512(password);
		System.out.println(encode);
		memberVO.setUserPass(encode);
		memberDAO.memberDelete(memberVO);
	}
	@Override
	public int memberIdChk(MemberVO memberVO) throws Exception{
		int result=memberDAO.memberIdChk(memberVO);
		return result;
	}
	
	@Override
	public List<MemberVO> memberList(SearchCriteria scri) throws Exception{
		List<MemberVO> list= memberDAO.memberList(scri);
		int size = list.size();
		for(int i =0;i<size;i++) {
			String decodePhoneNum = aes256.decrypt(list.get(i).getPhoneNum());
			String decodeEmail = aes256.decrypt(list.get(i).getEmail());
			list.get(i).setPhoneNum(decodePhoneNum);
			list.get(i).setEmail(decodeEmail);
		}
		return list;
	}
	@Override
	public int memberListCount(SearchCriteria scri) throws Exception{

		return memberDAO.memberListCount(scri);
	}
	
	@Override
	public MemberVO getByUserId(String userId) throws Exception{
		return memberDAO.getByUserId(userId);
	}
	
	@Override
	public String getByUserPass(String userId) throws Exception{
		return memberDAO.getByUserPass(userId);
	}
	
}
