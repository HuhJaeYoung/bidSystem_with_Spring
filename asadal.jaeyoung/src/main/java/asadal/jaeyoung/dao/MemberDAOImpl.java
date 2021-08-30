package asadal.jaeyoung.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void memberRegister(MemberVO memberVO) throws Exception{
		sqlSession.insert("memberMapper.memberRegister",memberVO);
	}
	
	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		
		return sqlSession.selectOne("memberMapper.memberLogin",memberVO);
	}
	
	@Override
	public void memberUpdate(MemberVO memberVO) throws Exception{
		sqlSession.update("memberMapper.memberUpdate",memberVO);
	}
	@Override
	public void memberDelete(MemberVO memberVO) throws Exception{
	
		sqlSession.delete("memberMapper.memberDelete",memberVO);
	}
	@Override
	public int memberIdChk(MemberVO memberVO) throws Exception{
		int result =sqlSession.selectOne("memberMapper.memberIdChk",memberVO);
		return result;
	}
	
	@Override
	public List<MemberVO> memberList(SearchCriteria scri) throws Exception{
		return sqlSession.selectList("memberMapper.memberListPage",scri);
	}
	@Override
	public int memberListCount(SearchCriteria scri) throws Exception{
		return sqlSession.selectOne("memberMapper.listCount",scri);
	}
	//유저가져오기 수정 될
	@Override
	public MemberVO getByUserId(String userId) throws Exception{
		return sqlSession.selectOne("memberMapper.getByUserId", userId);
	}
}
