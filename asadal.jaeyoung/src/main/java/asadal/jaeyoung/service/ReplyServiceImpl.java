package asadal.jaeyoung.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import asadal.jaeyoung.dao.ReplyDAO;
import asadal.jaeyoung.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Override
	public List<ReplyVO> replyRead(int board_id) throws Exception{
		return replyDAO.replyRead(board_id);
	}
	
	
	@Override
	public void replyWrite(ReplyVO replyVO,String writer) throws Exception{
		replyVO.setReply_writer(writer);
		replyDAO.replyWrite(replyVO);
	}
	
	@Override
	public void replyUpdate(ReplyVO replyVO) throws Exception{
		replyDAO.replyUpdate(replyVO);
	}
	
	@Override
	public void replyDelete(ReplyVO replyVO) throws Exception{
		replyDAO.replyDelete(replyVO);
	}
	
	@Override
	public ReplyVO replySelect(int reply_id) throws Exception{
		return replyDAO.replySelect(reply_id);
	}
	

}
