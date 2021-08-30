package asadal.jaeyoung.dao;

import java.util.List;

import asadal.jaeyoung.vo.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> replyRead(int board_id) throws Exception;
	
	public void replyWrite(ReplyVO replyVO) throws Exception;
	
	public void replyUpdate(ReplyVO replyVO) throws Exception;
	
	public void replyDelete(ReplyVO replyVO) throws Exception;
	
	public ReplyVO replySelect(int reply_id) throws Exception;


}
