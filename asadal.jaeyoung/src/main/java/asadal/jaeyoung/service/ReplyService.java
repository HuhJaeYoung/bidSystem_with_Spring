package asadal.jaeyoung.service;

import java.util.List;

import asadal.jaeyoung.vo.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> replyRead(int board_id) throws Exception;
	
	public void replyWrite(ReplyVO replyVO,String writer) throws Exception;
	
	public void replyUpdate(ReplyVO replyVO) throws Exception;
	
	public void replyDelete(ReplyVO replyVO) throws Exception;
	
	public ReplyVO replySelect(int reply_id) throws Exception;

}
