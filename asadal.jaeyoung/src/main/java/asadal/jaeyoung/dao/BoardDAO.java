package asadal.jaeyoung.dao;

import java.util.List;

import asadal.jaeyoung.paging.SearchCriteria;
import asadal.jaeyoung.vo.BoardVO;

public interface BoardDAO {
	public void boardWrite(BoardVO boardVO) throws Exception;
	
	//목록
	public List<BoardVO> boardList(SearchCriteria scri) throws Exception;
	
	//총 개수 게시물
	public int boardListCount(SearchCriteria scri) throws Exception;
	
	
	public BoardVO boardRead(int id) throws Exception;
	
	public void boardUpdate(BoardVO boardVO) throws Exception;
	
	public void boardDelete(int id) throws Exception;
	
	public void boardHit(int boardId) throws Exception;


}
