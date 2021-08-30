package asadal.jaeyoung.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import asadal.jaeyoung.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<ReplyVO> replyRead(int board_id) throws Exception{
		return sqlSession.selectList("replyMapper.replyRead", board_id);
	}

	@Override
	public void replyWrite(ReplyVO replyVO) throws Exception{
		sqlSession.insert("replyMapper.replyWrite", replyVO);
	}
	
	@Override
	public void replyUpdate(ReplyVO replyVO) throws Exception{
		sqlSession.update("replyMapper.replyUpdate",replyVO);
	}
	
	@Override
	public void replyDelete(ReplyVO replyVO) throws Exception{
		sqlSession.delete("replyMapper.replyDelete",replyVO);
	}
	
	@Override
	public ReplyVO replySelect(int reply_id) throws Exception{
		return sqlSession.selectOne("replyMapper.replySelect",reply_id);
	}

}
